Imports System
Imports System.IO

Module Program
    Sub Main()
        Using sr As New StreamReader("moby_dick_no_punctuation.txt")
            Dim text As String
            text = sr.ReadToEnd
            Dim wordCount As Dictionary(Of String, Integer)
            wordCount = CountWords(text)

            Dim wordCountPairs As List(Of KeyValuePair(Of String, Integer))
            wordCountPairs = wordCount.ToList
            SortWordsByCount(wordCountPairs)

            For Each wordCountPair In wordCountPairs
                Console.WriteLine(wordCountPair.Key.ToString & ": " & wordCountPair.Value.ToString)
            Next

            Console.ReadKey()
        End Using
    End Sub

    Function CountWords(text As String)
        Dim tokens As String()
        tokens = text.Split
        Dim wordCount As New Dictionary(Of String, Integer)
        For Each word As String In tokens
            If wordCount.ContainsKey(word) Then
                Dim occurrences As Integer
                occurrences = wordCount.Item(word)
                occurrences += 1
                wordCount.Remove(word)
                wordCount.Add(word, occurrences)
            Else
                wordCount.Add(word, 1)
            End If
        Next
        Return wordCount
    End Function

    Sub SortWordsByCount(words As List(Of KeyValuePair(Of String, Integer)))
        words.Sort(Function(kv1, kv2) kv1.Value.CompareTo(kv2.Value))
        words.Reverse()
    End Sub
End Module
