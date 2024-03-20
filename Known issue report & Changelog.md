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

```
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

### Factory

There are 2 types of factrories currently **AnswerFactory** and **QuestionFactory** both registers {adds to repo using facade singelton} the question/answer in the repo.
if it is already exist return the existing one. Fcatory uses a new Enum **QuestionType.**

### Command

All commands implements the same interface **ICommand** 
As the Design Pattern shows. Thay are stored in a Map on main,
There is a new enum for that named **ActionType**
##### As of now the menu actions used as commands Are:
- **AddAnswer**
- **AddQuestion**
- **DeleteQuestion**

### Observer/Listener

As requested there is one Listener **MenuActionCompleteListener** that implemets the **ActionCompleteObserver** Interface which has an Update methods with **ActionType** as its argument. The update method is called in main once the action is finished.

### Adapter
Changed the **Exam** class to be `hw3_Exam` and using the Adapter Pattern
created an Adapter class named **Exam** which inherited **hw3_Exam**
##### Code was changed in:
- **hw3_Exam** - i couldn't deal with the toString being changed and inside methods. **Effected lines:** [115,117,90,63]
- **Exam** - new file so **All of it**. 
