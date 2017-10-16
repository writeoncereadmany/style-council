const fs = require('fs')

const wordMap = {}

const incrementCount = (word) => {
    wordMap[word] = wordMap[word] ? wordMap[word] + 1 : 1
}

function handleChunk(chunk) {
    chunk.toString()
        .split(/\s+/)
        .map(word => word.toLowerCase())
        .forEach(incrementCount)
}

function getSortedArray() {
    return Object.keys(wordMap)
            .reduce((arr, key) => {
                arr.push({ key, value: wordMap[key] })
                return arr
            }, [])
            .sort((a, b) => b.value - a.value)
}

function countWords(path) {
    return new Promise((resolve, reject) => {
        fs.createReadStream(path)
            .on('data', chunk => handleChunk(chunk))
            .on('end', () => resolve(getSortedArray()))
            .on('error', reject)
    })
}

module.exports = {
    countWords
}