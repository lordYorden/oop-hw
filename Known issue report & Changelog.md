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

## Changelog

Changes are described per Class

### Answer

- changed answer to be id based **[effected lines: 10, 20, ,25-37, 45, 47 and 111]**
- added a  `serialVersionUID` based on `Repo.REPO_VERSION` **[line 10]**
- added `hashCode()` and changed `equals()` to the fit the New `Answer` type **[effected lines: 94-111]**

### AutomaticExam

moved the `generated` to be a member of the class and changed it to a `HashSet<Answer>` as a result i Depracted the wasGenrated function beacuse the HashSet already checks it **[effected lines: 43-58, 80, 74]**

### Exam

- added `Exam.MIN_ANSWERS_PER_QUESTION` and changed the condition accordingly **[effected lines: 20, 62]**
- changed question to use a `LinkedHashSet` instead of an array, used linked to keep the order of insertion. Added `Exam.maxQuestionCapacity` **[effected lines: 19-35, 47-53]**
- changed `repo.generateDefaultAnswers()` to return an `ArrayList` **[Effected lines: 59-66]**
- changed `toString()` to use **Itertor** (foreach) instead of for. **[Effected lines: 75-78]**
- changed the class `Equals()` method to the **HashSet** **[Effected lines: 100-109]**

### MenualExam

- moved `deleteAnswerFromAQuestion()` to be a **Static** function in `MultiSelectQuestion` **[line: 36, 59]**
- Added a **Pause massage** to critical Exceptions **[effected lines: 40-41, 47-48, 56]**
- Added a check for **duplicate question** in exam, Based on a **HashSet** **[Effected lines: 51-56]**

### MultiSelectQuestion

- Added **Iterator** support for Question and changed it to contain a **HashSet** instead of an Array, added a constructor for `hardCodedQuestions()`
  **[Effected lines: 9-35, 36-45, 50-66, 99-104 116-148, 170-174]**
- Added `hashCode()` method and changed `equals()` to fit **HashSet** **[Effected lines: 157-169]**
- changed `toString()` to an **Iterator** based print.
- Replaced `deleteAnswerByIndex()` to `deleteAnswerById()` **[Effected lines: 137-147]**
- move the function `deleteAnswerFromAQuestion()` from **Repo** to be a **Static** member of `MultiSelectQuestion`
- Added a `serialVersionUID` based on `Repo.REPO_VERSION` **[line 13]**

### OpenEndedQuestion

- Added `hashCode()` and changed `equals()` to the fit **HashSet** **[effected lines: 36-55]**
- Added a `serialVersionUID` based on `Repo.REPO_VERSION` **[line 11]**

### Question

- Added `hashCode()` and changed `equals()` to the fit **HashSet** **[effected lines: 74-90]**
- Added a `serialVersionUID` based on `Repo.REPO_VERSION` **[line 12]**

### TestMaker (Main)

- Changed `Scanner input` to be a **Static** member
  and changed **all** Static methods accordingly.
  **[effected lines: 15-19, 49-69]**

> also **all** of the Static methods in TestMaker

- Changed `addQuestion()` to fit `SOLID` by spliting it into two overloading methods (For OpenEnded and MultiSelect). **[effected lines: 275,279, 288-311, 470-476]**
- Changed `addQuestion(Repo, MultiSelectQuestion)` to use Itertor. **[effected lines: 293-298]**
- chaned `hardCodedQue()` to use **HashSet** instead of Array **[effected lines: 318-519]**
- Changed `appendAnswerToQuestion()` to check for duplication ana print an error **[effected lines: 198-218]**
- changed `getDifficultyFromUser()` and `getSubjectFromUser()` to an **ArrayList**. **[effected lines: 565-619]**

### Repo

- Changed `answers` to a **LinkedHashSet** and fitted the functions to `answer.id`
  **[Effected lines: 47-52, 100-106, 155-119, 267-287]**

> **Efected Functions:**
> addAnswer(String)
> getAnswerById(int)
> deleteQuestionById(int)
> selectAnswerFromRepo(Repo, Scanner)

- changed `generateDefaultAnswers()` in Repo to use an `ArrayList` **[Effected lines: 298-310]**
- changes `toString()` and `toStringAnswers()` to use **Iterator** **[Effected lines: 124-140, 166-182]**
- Changed `questions` to a **LinkedHashSet** **[line: 20, 30]**
- Added a `serialVersionUID` based on `Repo.REPO_VERSION` **[Effected lines: 15-18]**
