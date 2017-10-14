defmodule WordCount do
    def count_words(text) do
        text
            |> String.split(~r/\s+/)
            |> Enum.map(&String.downcase/1)
            |> Enum.reduce(%{}, fn x, acc -> Map.update(acc, x, 1, &(&1 + 1)) end)
    end

    def sort_words(word_count) do
        word_count
            |> Map.to_list()
            |> List.keysort(1)
            |> Enum.reverse()
    end
end

defmodule Main do
    {:ok, text} = File.read('moby_dick_no_punctuation.txt')
    output = text
        |> WordCount.count_words()
        |> WordCount.sort_words()
        |> Enum.map(fn {word, count} -> "#{word}: #{count}" end)
        |> Enum.join("\n")
    IO.puts(output)
end