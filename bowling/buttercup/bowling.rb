class Array

  def sum
    self.inject(0) {|sum,x| sum + x }
  end

end

class Frame
  
  attr_reader :remaining_pins
  
  def self.from(pins)
    if pins[0] == 10
      # frame is a strike
      Frame.new(pins[0], nil, pins[1..-1])
    else  
      # frame is other than a strike
      Frame.new(pins[0], pins[1], pins[2..-1])
    end
  end
  
  def initialize(first, second, remaining_pins)
    @first = first
    @second = second
    @remaining_pins = remaining_pins
  end
  
  def score
    return 10 + next_two_balls if strike?
    return 10 + next_ball if spare?
    return @first + @second
  end
  
  private
  
  def next_ball
    @remaining_pins[0]
  end
  
  def next_two_balls
    @remaining_pins[0] + @remaining_pins[1]
  end
  
  def spare?
    @first + @second == 10
  end

  def strike?
    @first == 10
  end

end

class BowlingGame
  
  def initialize
    @pins = []
  end

  def record(*pins)
    @pins = @pins + pins.flatten
    self
  end
    
  def score
    # return 0 if @pins.empty?
    remaining_pins = @pins
    frame_scores = []
    while !remaining_pins.empty? && frame_scores.size < 10   do
      frame = Frame.from(remaining_pins)
      frame_scores << frame.score
      remaining_pins = frame.remaining_pins
    end
    return frame_scores.sum
  end
  
end

describe BowlingGame do
  
  it "should start at zero" do
    expect(BowlingGame.new.score).to eql(0)
  end
  
  it "should score a simple frame" do
    expect(BowlingGame.new.record(0,6).score).to eql(6)
  end
  
  it "should score two simple frames" do
    expect(BowlingGame.new.record(0,6).record(2,3).score).to eql(6+5)
  end
  
  it "should score a spare" do
    expect(BowlingGame.new.record(0,10).record(2,3).score).to eql(12+5)
  end
  
  it "should score a strike" do
    expect(BowlingGame.new.record(10).record(2,3).score).to eql(15+5)
  end
  
  it "should score perfect game" do
    expect(BowlingGame.new.record([10] * 12 ).score).to eql(300)
  end
  
  it "should score heartbreak game" do
    expect(BowlingGame.new.record([10] * 11 ).record(9).score).to eql(299)
  end
  
end

