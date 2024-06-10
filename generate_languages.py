import random
import csv

def is_sardinas_patterson_code(words):
    current_set = set(words)
    next_set = set()

    while current_set:
        for word1 in words:
            for word2 in current_set:
                prefix_suffix = subtract_prefix(word2, word1)
                if prefix_suffix and prefix_suffix:
                    next_set.add(prefix_suffix)

                prefix_suffix = subtract_suffix(word2, word1)
                if prefix_suffix and prefix_suffix:
                    next_set.add(prefix_suffix)

        if "" in next_set:
            return False

        current_set = next_set
        next_set = set()

    return True

def subtract_prefix(word, prefix):
    if word.startswith(prefix):
        return word[len(prefix):]
    return None

def subtract_suffix(word, suffix):
    if word.endswith(suffix):
        return word[:-len(suffix)]
    return None

def generate_binary_word():
    length = random.randint(1, 7)
    return ''.join(random.choice('01') for _ in range(length))

def generate_language():
    num_words = random.randint(1, 10)
    return {generate_binary_word() for _ in range(num_words)}

def generate_languages(num_languages):
    return [generate_language() for _ in range(num_languages)]

def generate_csv(filename, num_languages):
    languages = generate_languages(num_languages)
    with open(filename, 'w', newline='') as csvfile:
        fieldnames = ['language', 'num_words', 'max_length', 'min_length', 'is_code']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)

        writer.writeheader()
        for language in languages:
            num_words = len(language)
            max_length = max(len(word) for word in language)
            min_length = min(len(word) for word in language)
            is_code = is_sardinas_patterson_code(language)
            writer.writerow({'language': ','.join(language), 'num_words': num_words,
                             'max_length': max_length, 'min_length': min_length,
                             'is_code': is_code})

generate_csv('languages.csv', 10)
