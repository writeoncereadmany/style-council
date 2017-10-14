WordCount = Struct.new(:word, :count)

class WordCounter
  
  def initialize(filename)
    @lines = File.readlines filename
  end
  
  def first(n)
    word_counts_sorted_by_size.first(n)
  end
  
  private
  
  def word_counts_sorted_by_size
    word_counts.sort_by(&:count).reverse
  end
  
  def word_counts
    lowercase_words.group_by(&:itself).collect{|word,occurences| WordCount.new(word, occurences.size) }
  end
  
  def lowercase_lines
    @lines.collect(&:downcase)
  end
  
  def lowercase_words
    lowercase_lines.collect(&:split).flatten
  end
  
end

WordCounter.new('./moby_dick_no_punctuation.txt').first(5).each {|word_count| puts "#{word_count.word} : #{word_count.count}"}


