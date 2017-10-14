content = File.readlines './moby_dick_no_punctuation.txt'
result = content.collect(&:downcase).collect(&:split).flatten.group_by(&:itself).collect{|k,v| [k,v.size]}.sort_by{|e| -e[1]}.first(5)
result.each {|r| puts "#{r[0]} : #{r[1]}"}
