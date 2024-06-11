# Contributing to the Happy HTTP Minecraft mod

Thank you for considering contributing to [Your Mod Name]! Here are some guidelines to help you get started.

## Table of Contents

1. [Code of Conduct](#code-of-conduct)
2. [How to Contribute](#how-to-contribute)
   - [Reporting Bugs](#reporting-bugs)
   - [Suggesting Features](#suggesting-features)
   - [Contributing Code](#contributing-code)
3. [Development Guidelines](#development-guidelines)
   - [Setting Up the Development Environment](#setting-up-the-development-environment)
   - [Coding Standards](#coding-standards)
   - [Pull Request Process](#pull-request-process)
4. [Using Issue Templates](#using-issue-templates)
5. [Community](#community)

## Code of Conduct

This project and everyone participating in it is governed by the [Contributor Covenant Code of Conduct](CODE_OF_CONDUCT.md). By participating, you are expected to uphold this code. Please report unacceptable behavior.

## How to Contribute

### Reporting Bugs

If you find a bug in the project, please report it by creating an issue in the [GitHub Issues](https://github.com/clapters/HappyHttpMod/issues) section.

**When reporting a bug, please include:**
- A clear and descriptive title.
- The version of Minecraft, mod loader (Forge, Fabric, etc.), and the mod version.
- Steps to reproduce the issue.
- Expected and actual behavior.
- Any relevant logs or crash reports.

### Suggesting Features

We welcome suggestions for new features! To suggest a feature, please open an issue with the title prefixed by `[Feature Request]`.

**When suggesting a feature, please include:**
- A clear and descriptive title.
- A detailed description of the feature and its purpose.
- Any benefits or use cases.
- If applicable, how you envision the feature being implemented.

### Contributing Code

1. **Fork the Repository**: Click the "Fork" button on the top right of the repository page to create a copy of the repository on your GitHub account.

2. **Clone the Forked Repository**: Clone the repository to your local machine.
   ```sh
   git clone [https://github.com/clapters/HappyHttpMod].git
   cd your-repo
   git checkout -b feature/your-feature-name
   ```

3. **Create a New Branch**: Create a branch for your work.
   ```sh
    git checkout -b feature/your-feature-name
   ```

4. Make Your Changes: Implement your changes, following the coding standards and guidelines.

5. Commit Your Changes: Commit your changes with a clear and descriptive commit message.
   ```sh
    git add .
    git commit -m "Add feature: your-feature-name"
   ```

6. Push Your Changes: Push your changes to your forked repository.
   ```sh
    git push origin feature/your-feature-name
   ```

7. Create a Pull Request: Go to the original repository and click on the "New Pull Request" button. Select your branch and provide a clear description of your changes.

Development Guidelines
Setting Up the Development Environment
Clone the Repository: Clone the repository to your local machine.

```sh
git clone [https://github.com/your-username/your-repo](https://github.com/clapters/HappyHttpMod).git
cd your-repo
```

Set Up Dependencies: Ensure you have the necessary dependencies installed, such as JDK, Gradle, or Maven.

Build the Project: Use Gradle or Maven to build the project.

```sh
./gradlew build
```

Run Tests: Ensure all tests pass.

```sh
./gradlew test
```

**Coding Standards**
Follow the Coding Standards to ensure code consistency and quality. Key points include:

Use 4 spaces for indentation.
Use descriptive naming conventions.
Document code with Javadoc.
Pull Request Process
Ensure your pull request adheres to the following guidelines:

It includes a clear description of the changes and the problem it solves.
It adheres to the coding standards.
It includes tests for the new functionality or bug fix.
It does not include unnecessary commits or changes.
Submit your pull request:

Title: Use a clear and descriptive title.
Description: Provide a detailed description of your changes, including links to related issues.
Address feedback:

Be responsive to feedback and make necessary changes.
Update your branch with the latest changes from the main branch if needed.
Using Issue Templates
We have several issue templates to help structure bug reports, feature requests, and other types of contributions. When creating a new issue, please use one of the following templates:

**Bug Report Template**
Template Name: Bug Report

Purpose: Report a bug or unexpected behavior in the mod.

Content:

```markdown
Kopier kode
---
name: Bug Report
about: Report a bug or unexpected behavior in the mod
title: "[Bug] "
labels: ['bug']
assignees: ''

---

**Minecraft Version:**
(e.g., 1.17.1)

**Mod Loader:**
(Forge, Fabric, NeoForge, Spigot)

**Mod Version:**
(Version of your mod where the bug was found)

**Client/Server:**
(Client, Server, or Both)

**Describe the Bug:**
A clear and concise description of what the bug is.

**To Reproduce:**
Steps to reproduce the behavior:
1. Go to '...'
2. Click on '....'
3. Scroll down to '....'
4. See error

**Expected Behavior:**
A clear and concise description of what you expected to happen.

**Screenshots:**
If applicable, add screenshots to help explain your problem.

**Logs/Crash Reports:**
Include any relevant logs or crash reports. 

**Additional Context:**
Add any other context about the problem here.

**Checklist:**
- [ ] I have searched the existing issues to ensure this is not a duplicate.
- [ ] I have provided a clear and concise description of the bug.
- [ ] I have included steps to reproduce the bug.
- [ ] I have included any relevant logs or crash reports.
```


**Feature Request Template**
Template Name: Feature Request

Purpose: Suggest a new feature or enhancement for the mod.

Content:

```markdown
---
name: Feature Request
about: Suggest a new feature or enhancement for the mod
title: "[Feature Request] "
labels: ['feature', 'enhancement']
assignees: ''

---

**Feature Description:**
A clear and concise description of what the feature is and what problem it solves. Explain why this feature is needed and how it will benefit users.

**Proposed Solution:**
Describe how you envision the feature to be implemented. Include details on the functionality and any UI/UX changes if applicable.

**Alternatives Considered:**
Describe any alternative solutions or features you've considered and why you believe they are not as suitable as the proposed solution.

**Additional Context:**
Add any other context or screenshots about the feature request here. If applicable, include mockups or design proposals.

**Checklist:**
- [ ] I have searched the existing issues and feature requests to ensure this is not a duplicate.
- [ ] I have provided a clear and detailed description of the feature.
- [ ] I have provided a clear and detailed proposed solution.
- [ ] I have considered any alternative solutions or features.
```



**Support Request Template**
Template Name: Support Request

Purpose: Ask for help or support with using the mod.

Content:

```
markdown
---
name: Support Request
about: Ask for help or support with using the mod
title: "[Support] "
labels: ['support']
assignees: ''

---

**Minecraft Version:**
(e.g., 1.17.1)

**Mod Loader:**
(Forge, Fabric, NeoForge, Spigot)

**Mod Version:**
(Version of your mod where the issue was found)

**Client/Server:**
(Client, Server, or Both)

**Describe Your Issue:**
A clear and concise description of what you need help with.

**Steps Taken:**
Describe the steps you have already taken to try to resolve the issue.

**Additional Context:**
Add any other context or screenshots about the issue here.

**Checklist:**
- [ ] I have searched the existing issues to ensure this is not a duplicate.
- [ ] I have described my issue clearly.
- [ ] I have included any relevant steps taken to resolve the issue.
```


**Performance Issue Template**
Template Name: Performance Issue

Purpose: Report performance problems such as lag or high CPU usage.

Content:

```
markdown
---
name: Performance Issue
about: Report performance problems such as lag or high CPU usage
title: "[Performance] "
labels: ['performance']
assignees: ''

---

**Minecraft Version:**
(e.g., 1.17.1)

**Mod Loader:**
(Forge, Fabric, NeoForge, Spigot)

**Mod Version:**
(Version of your mod where the issue was found)

**Client/Server:**
(Client, Server, or Both)

**Describe the Performance Issue:**
A clear and concise description of what the performance issue is.

**Steps to Reproduce:**
Steps to reproduce the behavior:
1. Go to '...'
2. Click on '....'
3. Scroll
```

