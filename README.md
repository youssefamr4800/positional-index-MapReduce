# Positional Index using MapReduce

This project implements a **Positional Index** using **Hadoop MapReduce** in Java. A positional index stores the positions of terms in documents, enabling advanced information retrieval techniques such as phrase queries and proximity search.

## 📌 Features

- Reads multiple text documents from HDFS.
- Constructs a positional index with the format:
  ```
  term    doc1: pos1, pos2, ..., doc2: pos1, ...
  ```
- Efficient use of Mapper, Reducer, and optional Combiner classes.
- Works with Hadoop MapReduce.

## 📁 Project Structure

```
positional-index-MapReduce/
│
├── src/
│   └── mr03/
│       └── inverted_index/
│           ├── PositionalIndexDriver.java
│           ├── PositionalIndexMapper.java
│           ├── PositionalIndexReducer.java
│           └── PositionalIndexCombiner.java (optional)
│
├── input/
│   └── doc1.txt, doc2.txt, ...
└── README.md
```

## 🚀 How to Run

### 1. Compile Java Code

```bash
hadoop com.sun.tools.javac.Main -d . src/mr03/inverted_index/*.java
```

### 2. Create a JAR

```bash
jar -cvf positional-index.jar mr03
```

### 3. Upload Input Files to HDFS

```bash
hdfs dfs -mkdir /input
hdfs dfs -put input/* /input/
```

### 4. Run the Job

```bash
hadoop jar positional-index.jar mr03.inverted_index.PositionalIndexDriver /input /output
```

### 5. View the Output

```bash
hdfs dfs -cat /output/part-r-00000
```

## 🛠 Technologies Used

- Java
- Hadoop MapReduce
- HDFS
- Linux

## 📌 Sample Output

```
angels    doc7: 1, doc8: 1, doc9: 1
brutus    doc1: 2, doc2: 2, doc4: 1
mercy     doc1: 5, doc3: 1, doc4: 3, doc5: 2
```

## 👨‍💻 Author

**Youssef Amr**  
[GitHub](https://github.com/youssefamr4800)

---
