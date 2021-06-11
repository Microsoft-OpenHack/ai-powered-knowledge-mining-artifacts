import json
from string import punctuation
from collections import Counter

def get_top_ten_words(text):

    # Array of stop words to be ignored
    stopwords = ['', 'i', 'me', 'my', 'myself', 'we', 'our', 'ours', 'ourselves', 'you', 
    "youre", "youve", "youll", "youd", 'your', 'yours', 'yourself', 
    'yourselves', 'he', 'him', 'his', 'himself', 'she', "shes", 'her', 
    'hers', 'herself', 'it', "its", 'itself', 'they', 'them', 
    'their', 'theirs', 'themselves', 'what', 'which', 'who', 'whom', 
    'this', 'that', "thatll", 'these', 'those', 'am', 'is', 'are', 'was',
    'were', 'be', 'been', 'being', 'have', 'has', 'had', 'having', 'do', 
    'does', 'did', 'doing', 'a', 'an', 'the', 'and', 'but', 'if', 'or', 
    'because', 'as', 'until', 'while', 'of', 'at', 'by', 'for', 'with', 
    'about', 'against', 'between', 'into', 'through', 'during', 'before', 
    'after', 'above', 'below', 'to', 'from', 'up', 'down', 'in', 'out', 
    'on', 'off', 'over', 'under', 'again', 'further', 'then', 'once', 'here', 
    'there', 'when', 'where', 'why', 'how', 'all', 'any', 'both', 'each', 
    'few', 'more', 'most', 'other', 'some', 'such', 'no', 'nor', 'not', 
    'only', 'own', 'same', 'so', 'than', 'too', 'very', 'can', 'will',
    'just', "dont", 'should', "shouldve", 'now', "arent", "couldnt", 
    "didnt", "doesnt", "hadnt", "hasnt", "havent", "isnt", "mightnt", "mustnt",
    "neednt", "shant", "shouldnt", "wasnt", "werent", "wont", "wouldnt", "shall"]

    # Empty JSON structure in which to return the results
    result_json = {"words":[]}

    try:
        # remove numeric digits
        text = ''.join(c for c in text if not c.isdigit())

        # remove punctuation and make lower case
        text = ''.join(c for c in text if c not in punctuation).lower()

        # remove stopwords
        text = ' '.join(w for w in text.split() if w not in stopwords)

        # Count the words and get the most common 10
        wordcount = Counter(text.split()).most_common(10)
        words = [w[0] for w in wordcount]

        # Add the top 10 words to the output for this text record
        result_json["words"] = words

        # return the results
        return result_json

    except Exception as ex:
        print(ex)

# Test the function
test_text = "Four score and seven years ago our fathers brought forth on this continent, a new nation, conceived in Liberty, and dedicated to the proposition that all men are created equal."\
            "Now we are engaged in a great civil war, testing whether that nation, or any nation so conceived and so dedicated, can long endure. We are met on a great battlefield of that war." \
            "We have come to dedicate a portion of that field, as a final resting place for those who here gave their lives that that nation might live. It is altogether fitting and proper that we should do this."\
            "But, in a larger sense, we can not dedicate, we can not consecrate, we can not hallow this ground. The brave men, living and dead, who struggled here, have consecrated it, far above our poor power to add or detract."\
            "The world will little note, nor long remember what we say here, but it can never forget what they did here. It is for us the living, rather, to be dedicated here to the unfinished work which they who fought here have thus far so nobly advanced."\
            "It is rather for us to be here dedicated to the great task remaining before us that from these honored dead we take increased devotion to that cause for which they gave the last full measure of devotion, that we here highly resolve that these dead shall not have died in vain; that this nation, under God, shall have a new birth of freedom and that government of the people, by the people, for the people, shall not perish from the earth."

test_result = get_top_ten_words(test_text)

print(json.dumps(test_result))