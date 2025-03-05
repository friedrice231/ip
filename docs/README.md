# PawPal Chat Assistant - User Guide

Welcome to **PawPal**, your friendly chat assistant for managing tasks efficiently! 🐾 This guide will help you get started with using PawPal and its features.

---

## 📌 Table of Contents
1. [Introduction](#introduction)
2. [Getting Started](#getting-started)
3. [Features](#features)
    - [Adding Tasks](#adding-tasks)
    - [Listing Tasks](#listing-tasks)
    - [Marking Tasks as Done](#marking-tasks-as-done)
    - [Deleting Tasks](#deleting-tasks)
    - [Task Types](#task-types)
4. [Command Summary](#command-summary)
5. [FAQ](#faq)
6. [Troubleshooting](#troubleshooting)

---

## 🐱 Introduction
PawPal is a simple, interactive chatbot that helps you manage your daily tasks with ease. Whether it’s tracking tasks, setting deadlines, or organizing your schedule, PawPal is here to assist you with a purr-sonalized experience!

![PawPal Screenshot](/docs/Ui.png)
---

## 🚀 Getting Started

1. **Run PawPal**: Open the application on your device.
2. **Start Chatting**: Type your commands in the chatbox and hit "Send" or press "Enter".
3. **Manage Tasks**: Add, list, and complete your tasks easily.

---

## ✨ Features

### 📋 Adding Tasks
You can add different types of tasks:
- **ToDos**: Simple tasks without deadlines.
- **Deadlines**: Tasks with a specific due date.
- **Events**: Tasks that occur within a time range.

💡 **Example Commands:**
```plaintext
todo read  
deadline test /by tomorrow  
event test /from today /to tomorrow  
```

### 📃 Listing Tasks
View all your tasks using the `list` command.  
💡 **Example:**
```plaintext
list
```
PawPal will display your tasks in a structured format.

### ✅ Marking Tasks as Done
Once you’ve completed a task, mark it as done!  
💡 **Example:**
```plaintext
done 1
```
This marks task #1 as completed.

### 🗑️ Deleting Tasks
Remove a task from your list using the delete command.  
💡 **Example:**
```plaintext
delete 2
```
This removes task #2 from your task list.

### 🏷️ Task Types
- **`[T]`**: ToDo task
- **`[D]`**: Deadline task
- **`[E]`**: Event task
- **`[X]`**: Completed task

Example of listed tasks:
```plaintext
1. [T][X] run  
2. [T][ ] read  
3. [D][ ] test (by: tomorrow)  
4. [E][ ] test from: today to: tomorrow  
```

---

## 📜 Command Summary

| Command | Description | Example |
|---------|-------------|---------|
| `todo <task>` | Adds a ToDo task | `todo read` |
| `deadline <task> /by <date>` | Adds a Deadline task | `deadline test /by tomorrow` |
| `event <task> /from <date> /to <date>` | Adds an Event task | `event test /from today /to tomorrow` |
| `list` | Lists all tasks | `list` |
| `done <task number>` | Marks a task as done | `done 1` |
| `delete <task number>` | Deletes a task | `delete 2` |

---

## ❓ FAQ

**Q1: How do I edit a task?**  
Currently, PawPal does not support editing tasks. You can delete and re-add a task instead.

**Q2: Can I undo a completed task?**  
No, but you can re-add the task if needed.

**Q3: What happens if I enter an invalid command?**  
PawPal will notify you and prompt you to enter a valid command.

---

## 🔧 Troubleshooting

| Issue | Possible Cause | Solution |
|-------|--------------|----------|
| Task is not marked as done | Incorrect task number | Check the task list and try again |
| Command not recognized | Typo or incorrect format | Refer to the [Command Summary](#command-summary) |
| Task not deleted | Task number does not exist | Use `list` to check task numbers |

---

PawPal is here to help you stay organized in a fun and interactive way! 🐾 Happy task managing! 🎉