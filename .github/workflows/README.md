# Mobile Automation CI/CD Configuration

## Overview
This directory contains GitHub Actions workflows for CI/CD automation.

## Workflows

### 1. CI/CD Pipeline (`ci-cd-pipeline.yml`)
**Triggers:**
- Push to main/master/develop branches
- Pull requests to main/master
- Manual workflow dispatch

**Jobs:**
- **Build & Validate**: Compiles code and runs quality checks
- **Run Mobile Tests**: Executes tests on Android emulator (matrix strategy)
- **Run All Tests**: Executes complete test suite
- **Generate Reports**: Collects and publishes test reports

**Features:**
- Parallel test execution
- Automatic screenshot capture on failure
- Artifact retention for 30 days
- Manual test suite selection

### 2. Code Quality (`code-quality.yml`)
**Triggers:**
- Push to main/master
- Pull requests
- Weekly schedule (Monday 9 AM UTC)

**Jobs:**
- **Code Quality Check**: PMD and SpotBugs analysis
- **Security Scan**: OWASP dependency vulnerability check
- **Code Coverage**: JaCoCo coverage reports

### 3. Release Automation (`release.yml`)
**Triggers:**
- Tag push (v*)
- Manual workflow dispatch

**Jobs:**
- **Build Release Package**: Creates distribution JAR
- **Create GitHub Release**: Automated release with notes

## Setup Instructions

### 1. Repository Secrets (Optional)
Add these secrets in GitHub > Settings > Secrets and variables > Actions:
- `GITHUB_TOKEN` (automatically provided)
- Custom secrets for notifications (Slack, Email, etc.)

### 2. Enable Workflows
Workflows are automatically enabled when pushed to the repository.

### 3. Manual Execution
1. Go to Actions tab in GitHub
2. Select the workflow
3. Click "Run workflow"
4. Choose parameters (if applicable)
5. Click "Run workflow"

## Artifacts
After workflow execution, you can download:
- Test reports (HTML)
- Failure screenshots
- Code quality reports
- Security scan results
- Coverage reports

## Customization

### Add New Test Suites
Edit `ci-cd-pipeline.yml` and add to the matrix:
```yaml
strategy:
  matrix:
    test-suite: [login, signup, registration, signin, your-new-test]
```

### Change Java Version
Update `JAVA_VERSION` in workflow files.

### Add Notifications
Add steps for Slack, Teams, or email notifications in the workflows.

## Troubleshooting

### Emulator Issues
- Check Android API level compatibility
- Ensure sufficient disk space (minimum 10GB)
- Review emulator logs in workflow output

### Test Failures
- Download failure screenshots from artifacts
- Check test-output directory for detailed logs
- Review ExtentReports in the reports folder

### Build Failures
- Verify Java version compatibility
- Check Maven dependency resolution
- Review compilation errors in workflow logs

## Best Practices
1. Keep workflows modular and reusable
2. Use matrix strategy for parallel execution
3. Cache dependencies for faster builds
4. Set appropriate artifact retention periods
5. Monitor workflow execution times
6. Regularly update action versions
