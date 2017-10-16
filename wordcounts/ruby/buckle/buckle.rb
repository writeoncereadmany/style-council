class FileSorter
  def initialize(file_name)
    @file_name = file_name
    @words = count_words_and_sort_by_frequency()
  end

  def count_words_and_sort_by_frequency()
      File.foreach(@file_name).flat_map do |line|
        line
          .split(" ")
          .group_by{|word| word.downcase}
          .map{|word, words|[word, words.size]}
    end
  end
end
