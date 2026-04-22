# CI/CD Quick Start Guide

## 🚀 Getting Started in 5 Minutes

### Step 1: Push to GitHub
```bash
cd "/home/wadmin/Downloads/Mobile_automation1 (1)/Mobile_automation"
git add .
git commit -m "Add CI/CD pipeline configuration"
git push origin main
```

### Step 2: View Workflows
1. Go to: https://github.com/smanna-log/rooms
2. Click on **Actions** tab
3. You'll see 3 workflows:
   - ✅ CI/CD Pipeline - Mobile Automation
   - ✅ Code Quality & Security Scan
   - ✅ Release Automation

### Step 3: Trigger First Run
Workflows will automatically run on:
- **Push** to main/master/develop branches
- **Pull Request** to main/master

Or run manually:
1. Go to Actions tab
2. Select "CI/CD Pipeline - Mobile Automation"
3. Click "Run workflow"
4. Choose test suite (or run all)
5. Click "Run workflow"

---

## 📋 What's Included

### Workflow 1: CI/CD Pipeline
- ✅ Automatic build validation
- ✅ Test execution on Android emulator
- ✅ Parallel test execution (matrix strategy)
- ✅ Screenshot capture on failure
- ✅ Test reports as artifacts
- ✅ Manual test suite selection

**Test Suites:**
- LoginTest
- SignupTest
- RegistrationTest
- SignInTest (newly added with negative tests)

### Workflow 2: Code Quality
- ✅ PMD code analysis
- ✅ SpotBugs static analysis
- ✅ OWASP security scan
- ✅ Code coverage reports
- ✅ Weekly scheduled runs

### Workflow 3: Release Automation
- ✅ Automated release builds
- ✅ GitHub release creation
- ✅ Release notes generation
- ✅ Artifact packaging

---

## 🎯 Usage Examples

### Run Specific Test Suite
```yaml
# In GitHub Actions > Run workflow
test_suite: signin  # or login, signup, registration, all
```

### Create a Release
```bash
# Tag your release
git tag v1.0.0
git push origin v1.0.0
```

### View Test Reports
1. Go to Actions tab
2. Click on completed workflow run
3. Scroll to "Artifacts" section
4. Download reports (available for 30 days)

---

## 🔧 Configuration Files Created

```
.github/
├── workflows/
│   ├── ci-cd-pipeline.yml      # Main CI/CD pipeline
│   ├── code-quality.yml        # Code quality & security
│   ├── release.yml             # Release automation
│   ├── README.md               # Detailed documentation
│   ├── .gitignore              # Workflow-specific ignores
│   └── setup-ci-cd.sh          # Local setup helper
```

---

## 📊 What You'll See

### On Push/PR:
```
✓ Build & Validate
✓ Run Mobile Tests (parallel)
  ├── Login Tests
  ├── Signup Tests
  ├── Registration Tests
  └── SignIn Tests
✓ Generate Reports
```

### Artifacts Generated:
- 📄 Test Reports (HTML)
- 📸 Failure Screenshots
- 🔍 Code Quality Reports
- 🛡️ Security Scan Results
- 📈 Coverage Reports

---

## ⚙️ Customization

### Add New Tests to Pipeline
Edit `.github/workflows/ci-cd-pipeline.yml`:
```yaml
strategy:
  matrix:
    test-suite: [login, signup, registration, signin, YOUR_NEW_TEST]
```

### Change Java Version
Update in all workflow files:
```yaml
env:
  JAVA_VERSION: '17'  # Change to your version
```

### Add Notifications
Add to any workflow:
```yaml
- name: Notify Slack
  if: failure()
  uses: slackapi/slack-github-action@v1
  with:
    channel-id: 'your-channel'
    slack-message: 'Tests failed!'
  env:
    SLACK_BOT_TOKEN: ${{ secrets.SLACK_BOT_TOKEN }}
```

---

## 🐛 Troubleshooting

### Workflows Not Showing?
- Check `.github/workflows/` directory exists
- Ensure YAML syntax is valid
- Push to correct branch (main/master)

### Tests Failing in CI?
1. Download failure screenshots from artifacts
2. Check if emulator is configured correctly
3. Verify test data and credentials
4. Review workflow logs

### Emulator Issues?
- GitHub runners have limited resources
- Consider using cloud device farms (BrowserStack, Sauce Labs)
- Reduce API level for faster startup

---

## 📚 Next Steps

1. ✅ Push code to GitHub
2. ✅ Monitor first workflow run
3. ✅ Download and review test reports
4. ✅ Customize workflows as needed
5. ✅ Set up notifications (optional)
6. ✅ Configure branch protection rules

---

## 🔗 Useful Links

- **GitHub Actions Docs**: https://docs.github.com/en/actions
- **Workflow Syntax**: https://docs.github.com/en/actions/using-workflows/workflow-syntax-for-github-actions
- **Your Repository**: https://github.com/smanna-log/rooms
- **Actions Tab**: https://github.com/smanna-log/rooms/actions

---

## 💡 Pro Tips

1. **Cache Dependencies**: Already configured for faster builds
2. **Matrix Strategy**: Tests run in parallel to save time
3. **Artifact Retention**: Set to 30 days (configurable)
4. **Manual Triggers**: Use workflow_dispatch for on-demand runs
5. **Scheduled Runs**: Code quality runs weekly automatically

---

**Need Help?**
Check the detailed documentation in `.github/workflows/README.md`
