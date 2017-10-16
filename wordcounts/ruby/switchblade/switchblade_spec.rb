require 'file_sorter_version_1'

describe FileSorter do
  let(:file_sorter) { described_class.new('./spec/test-input.txt') }

  it "counts and sorts the words in a file by frequency" do
    expected_output = [["this", 3], ["is", 2], ["a", 2], ["file", 1], ["of", 1], ["words", 1], ["for", 1], ["word", 1], ["sorting", 1], ["thing.", 1]]
    expect(file_sorter.count_words_and_sort_by_frequency()).to eq expected_output
  end

end
