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

## SOLID Priciples

1) Single Responsibility Principle
2) Open/Closed Principle
3) Liskov Substitution Principle
4) Interface Segregation Principle
5) Dependency Inversion Principle

## Changelog and Breaches

Changes are described per Breach

### Answer, Question (And its children) Got Inteface Solutionable

- by containg a displaySolution method and member instead (that used get and set) instead of an Abstruction layer that returns A String with the solution details.
- berched priciples that were fixed (1, 2, and 5).

### Repo, Exam got a new Member, QuestionManger, Question is now Comparable By Id (as a default, Comperator as an Option)

- Not only you could have used only ID before to find a Question, both Repo and Exam Contained a collection of Questions that basaicly did the same thing.
- Breached priciples that were fixed (1, 2).

### Updated the Examable Inteface

- Examble has now a method that return A question from repo
  both AutomaticExam and MenualExam no longer craeted the exam and add questions, Instead the Abstract Class Exam usess the new mehtod to get a question to add, without knowing how.
- Breached priciples that were fixed (2, 5).

### Repo got an Interface DefaultAnswers

> *Name is a work in progress

- Repo has 2 new methods for the default answers. each of them get a boolean and Returns answer to fit it. Exam now uses it to genarate the default answers for each question.
- Breaches principle that were fixed (2,5).

### Both MultiSelectQuestion and Repo got a new member, Answer Manager

* Not only you could have used only ID before to find a Answer, both Repo and MultiSelectQuestion Contained a collection of Answers that basaicly did the same thing.
* Breached priciples that were fixed (1, 2).

### Question and Answers Manager got a new Generic Abstract class ElementManager

* When updating the Class Digram i noticed both AnswersManager and QuestionManager has basicly the same members and function then i remembered Generics is a thing so I added the ElementManager, i also changed function that requires AnswerManager or QuestionManager to use ElementManager.
* Breached priciples that were fixed (2, 5, 3).
