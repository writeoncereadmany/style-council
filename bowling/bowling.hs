import Control.Arrow

data Bonus = Bonus { points :: Int, rolls :: Int }
noBonus = Bonus { points = 0, rolls = 0}

data Frame = Start
           | FirstBall Int
           | Complete Int Bonus

data Game = Game { completed :: [Frame], current :: Frame }

class Score a where
  score :: a -> Int

instance Score Frame where
  score Start = 0
  score (FirstBall n) = n
  score (Complete pins bonus) = pins + points bonus

instance Score Game where
  score game = sum (score <$> completed game) + score (current game)

class ApplyBonus a where
  applyBonus :: Int -> a -> a

instance ApplyBonus Bonus where
  applyBonus pins bonus@(Bonus { rolls = 0 }) = bonus
  applyBonus pins (Bonus { points = p, rolls = r }) = Bonus { points = p + pins, rolls = r - 1 }

instance ApplyBonus Frame where
  applyBonus pins (Complete points bonus) = Complete points (applyBonus pins bonus)
  applyBonus pins frame = frame

instance ApplyBonus Game where
  applyBonus pins game = Game { completed = (applyBonus pins) <$> (completed game), current = current game }

rollAll :: [Int] -> Int
rollAll rolls = score $ foldl (flip roll) Game { completed = [], current = Start } rolls

roll :: Int -> Game -> Game
roll pins = applyBonus pins
        >>> progressGame pins

progressGame :: Int -> Game -> Game
progressGame pins game
  | length (completed game) == 10 = game
  | otherwise = case knockDown pins (current game) of
     frame@(Complete _ _) -> nextFrame frame game
     frame                -> game { current = frame }

nextFrame :: Frame -> Game -> Game
nextFrame frame game = Game { completed = completed game ++ [frame], current = Start }

knockDown :: Int -> Frame -> Frame
knockDown pins Start           = if pins == 10
                                 then Complete 10 (noBonus { rolls = 2})
                                 else FirstBall pins
knockDown pins (FirstBall n)   = if pins + n == 10
                                 then Complete 10 (noBonus { rolls = 1 })
                                 else Complete (pins + n) noBonus
knockDown pins (Complete _ _ ) = error "Should not be bowling towards a completed frame"
