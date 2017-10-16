use std::collections::HashMap;

use std::ascii::AsciiExt;
use std::fs::File;
use std::io::prelude::*;

type Text = String;
type Word = String;
type Occurrences = u32;
type UnsortedResults = HashMap<Word, Occurrences>;

fn load_text() -> Text {
    let mut file = File::open("moby_dick_no_punctuation.txt").unwrap();
    let mut contents = String::new();
    file.read_to_string(&mut contents).unwrap();
    contents = contents + " ";
    contents.to_ascii_lowercase()
}

fn print_result(result: Vec<(&Word, &Occurrences)>) {
    for r in result.iter() {
        println!("{}: {}", r.0, r.1)
    }
}

fn empty_word() -> Word {
    "".to_string()
}

fn is_word(word: Word) -> bool {
    if word != empty_word() {
        true
    } else {
        false
    }
}

fn is_end_of_word(c: char) -> bool {
    if c == ' ' || c == '\n' {
        true
    } else {
        false
    }
}

fn increment_word_count(word: Word, mut result: UnsortedResults) -> UnsortedResults {
    if is_word(word.clone()) {
        let occurrences: Occurrences = match result.get(&word) {
            Some(v) => v + 1,
            None => 1,
        };
        result.insert(word, occurrences);
    }

    result
}

fn count_words(text: Text) -> UnsortedResults {
    let mut result: UnsortedResults = HashMap::new();
    let mut word: Word = empty_word();

    for c in text.chars() {
        if is_end_of_word(c) {
            result = increment_word_count(word, result);
            word = empty_word();
        } else {
            word = word + &c.to_string();
        }
    }

    result
}

pub fn main() {
    let text: Text = load_text();

    let result: UnsortedResults = count_words(text);

    let mut ordered_results: Vec<(&Word, &Occurrences)> = result.iter().collect();
    ordered_results.sort_by(|a, b| b.1.cmp(a.1));

    print_result(ordered_results)
}


#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_works() {
    }

    #[test]
    fn test_count_words() {
        let text: Text = "this is the text";
        let result = count_words(text);

        assert_eq!(result.get("this"), 1)
    }
}
