import Data.MultiSet (insert, toOccurList)
import qualified Data.MultiSet as Bag
import Control.Arrow
import Data.Char
import Data.Ord
import Data.List (sortBy)

main :: IO ()
main = do content <- readFile "moby_dick_no_punctuation.txt" 
          mapM_ putStrLn (counts content)

counts :: String -> [String]
counts = map toLower
     >>> words
     >>> foldl (flip insert) Bag.empty
     >>> toOccurList
     >>> sortBy (comparing snd)
     >>> reverse
     >>> map show
