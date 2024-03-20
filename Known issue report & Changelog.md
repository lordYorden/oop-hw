# Known Issues Report & Changelog

## How to use?

1) Select a **Subject** and load the matching Repo

```
Select a subject: 
1. Math
2. Science
3. History
4. Geography
```

2) You will get the following menu

- if no questions appear when selecting option 1, Uncomment the `hardCodedQue()` call in main and run it again.

```
Select a subject: 
Welcome to my Test Maker!
Please select an option: (-1 to exit)
1. Display all of the question from the repo (and their answers)
2. Add a new answers to the repo
3. Append an answer to an existing question
4. Add a new question
5. Delete an answers to a question
6. Delete a question
7. Generate a new test
-1 to exit
```

3) Enter your selction and follow the instructions - once you finish generating a test, you will get the **following massage** (For example):

```
Test written seccefully you can find it in exam_2024_02_12_18_13.txt
Press any key to continue...
```

- The Solution will be in the same folder with the same name (For example):
  Solution_2024_02_12_18_13.txt

## Design Patterns used

1) Facade
2) Factory
3) Command
4) Obesrver
5) Adapter

## Changelog

Changes are described per Design Patterns

### Facade

created a Facade class named **ExamMakerFacade**, repo is accessed only through the facade.
The save and load of the repo in now in the facaed control using the **RepoBackupable** interface;

### Singelton

I choose the acesses point to be the facade becuase it manages the repo, and is used in the factroy methods to register new questions.

### Fcatory

There are 2 types of factrories currently **AnswerFactory** and **QuestionFactory** both register the question/answer in the repo.
if it is already exist return the existing one. Fcatory uses a new Enum **QuestionType.**



## Classes that didn't change

- ExamCreationException
- NumOfAnswersException
- NumOfQuestionsException
