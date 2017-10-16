class FileSorter

  DELIMITER = " "

  def initialize(file_path)
    @file_path = file_path
    @unique_words_with_count = Hash.new(0)
    @words = File.read(file_path).split(DELIMITER)
  end


  def count_words_and_sort_by_frequency()
    @words.each { words[word] +=1 }
          .sort_by{|_key, value| value}
          .reverse
  end
end
