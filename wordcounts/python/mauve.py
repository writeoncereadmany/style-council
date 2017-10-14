import collections

def sorted_token_histogram(tokens):
    token_count = collections.defaultdict(lambda: 0)
    for token in tokens:
        token_count[token] += 1

    return sorted(token_count.items(), key=lambda i: i[1], reverse=True)

if __name__ == '__main__':  
    INPUT_PATH='moby_dick_no_punctuation.txt'
    with open(INPUT_PATH) as input:
        tokens = input.read().split()

        sorted_token_count = sorted_token_histogram(tokens)

        for token, count in sorted_token_count:
            print(token)