# Project Management:
1. Create, edit, and delete projects.
2. Define project timelines and objectives.
3. Assign project managers or leads.
# Task Management:
1. Kanban boards for visual task management.
2. Task statuses: To-Do, In Progress, Done (customizable).
3. Assign tasks to multiple team members.
4. Set task priorities (High, Medium, Low).
5. Due dates with reminders.
# Dependency Management:
1. Link tasks with dependencies (e.g., Task B cannot start until Task A is completed).
2. Visualize dependencies using Gantt charts.
# Custom Labels/Tags:
1. Add labels or tags to tasks for easier categorization.
2. Filter tasks by labels.
# AI Suggestions:
1. Recommend task assignments based on workload.
2. Predict project delays based on current progress.
3. Provide smart reminders for overdue tasks.

# Tech Stack:
Backend: Java Spring Boot (REST APIs, WebSocket for real-time updates).
Database: MongoDB, Redis for caching.
Frontend: React.
Deployment: Docker, Kubernetes, AWS/GCP/Azure for cloud hosting.

## Core Features
* create a project
* that project has a due date which can be a range 
* project has components
* components are either internal apps or external services
* for each component track its availability
* if component is already being used by another project, then it is not available fully, its marked as conflicted. this makes the overall project status red
* there are some states that the project can be in where it can be available to other projects
* if the component is required in a project in a certain state, then it should be in a different state in another project
* track the overall project state based on how all its components are progressing
* have a timeline view where each project can be visualized with all its encompassing components
* allow input of all these things into the software
* no users for this software
* changes are real time and also saved into db using rest api

* ![erd copy](https://github.com/user-attachments/assets/2bc5b0a0-ed1a-4351-82e5-d331c1a287c8)
