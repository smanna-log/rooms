# Deployment Guide - Mobile Automation CI/CD

## 📦 Deploy to GitHub Repository

### Option 1: Using HTTPS
```bash
cd "/home/wadmin/Downloads/Mobile_automation1 (1)/Mobile_automation"

# Initialize git if not already done
git init

# Add remote repository
git remote add origin https://github.com/smanna-log/rooms.git

# Add all files
git add .

# Commit
git commit -m "feat: Add CI/CD pipeline configuration with automated testing"

# Push to main branch
git branch -M main
git push -u origin main
```

### Option 2: Using SSH
```bash
cd "/home/wadmin/Downloads/Mobile_automation1 (1)/Mobile_automation"

# Initialize git if not already done
git init

# Add remote repository using SSH
git remote add origin git@github.com:smanna-log/rooms.git

# Add all files
git add .

# Commit
git commit -m "feat: Add CI/CD pipeline configuration with automated testing"

# Push to main branch
git branch -M main
git push -u origin main
```

---

## 🔐 SSH Key Setup (If Using SSH)

### Generate SSH Key (if not exists)
```bash
ssh-keygen -t ed25519 -C "your_email@example.com"
```

### Add SSH Key to GitHub
1. Copy your public key:
   ```bash
   cat ~/.ssh/id_ed25519.pub
   ```
2. Go to GitHub > Settings > SSH and GPG keys
3. Click "New SSH key"
4. Paste the key and save

### Test SSH Connection
```bash
ssh -T git@github.com
```

---

## ✅ Verify CI/CD Setup

### After Push, Check:
1. **GitHub Actions Tab**: https://github.com/smanna-log/rooms/actions
2. **Workflow Status**: Should show running/completed workflows
3. **Artifacts**: Test reports and screenshots available for download

### Manual Trigger Test
```bash
# Go to Actions tab and manually run:
# 1. CI/CD Pipeline - Mobile Automation
# 2. Select test suite: "all" or specific test
# 3. Click "Run workflow"
```

---

## 🚀 Production Deployment Checklist

### Pre-Deployment
- [ ] All tests passing locally
- [ ] Code reviewed and approved
- [ ] No sensitive data in code (use GitHub Secrets)
- [ ] README and documentation updated
- [ ] `.gitignore` properly configured

### Post-Deployment
- [ ] Workflows running successfully
- [ ] Test reports generated
- [ ] No security vulnerabilities detected
- [ ] Code quality metrics acceptable
- [ ] Team notified of CI/CD setup

---

## 📊 Monitoring & Maintenance

### Daily
- Check workflow execution status
- Review failed tests and fix issues
- Monitor artifact storage usage

### Weekly
- Review code quality reports
- Check security scan results
- Update dependencies if needed

### Monthly
- Clean up old artifacts
- Review and optimize workflow performance
- Update GitHub Actions versions

---

## 🔧 Advanced Configuration

### Add Branch Protection
1. Go to Settings > Branches
2. Add rule for `main` branch
3. Enable:
   - Require pull request reviews
   - Require status checks to pass
   - Include CI/CD Pipeline workflow

### Add Environment Variables
1. Go to Settings > Secrets and variables > Actions
2. Add repository variables:
   - `JAVA_VERSION`
   - `APPIUM_VERSION`
   - Custom variables as needed

### Add Secrets
1. Go to Settings > Secrets and variables > Actions
2. Add secrets:
   - `SLACK_BOT_TOKEN` (for notifications)
   - `BROWSERSTACK_ACCESS_KEY` (for cloud testing)
   - Other sensitive credentials

---

## 🌐 Cloud Testing Integration (Optional)

### BrowserStack
```yaml
# Add to workflow
- name: Run on BrowserStack
  run: mvn test -Dbrowserstack -Dbrowserstack.user=$BROWSERSTACK_USERNAME -Dbrowserstack.key=$BROWSERSTACK_ACCESS_KEY
  env:
    BROWSERSTACK_USERNAME: ${{ secrets.BROWSERSTACK_USERNAME }}
    BROWSERSTACK_ACCESS_KEY: ${{ secrets.BROWSERSTACK_ACCESS_KEY }}
```

### Sauce Labs
```yaml
# Add to workflow
- name: Run on Sauce Labs
  run: mvn test -Dsaucelabs -Dsaucelabs.user=$SAUCELABS_USERNAME -Dsaucelabs.key=$SAUCELABS_ACCESS_KEY
  env:
    SAUCELABS_USERNAME: ${{ secrets.SAUCELABS_USERNAME }}
    SAUCELABS_ACCESS_KEY: ${{ secrets.SAUCELABS_ACCESS_KEY }}
```

---

## 📈 Performance Optimization

### Speed Up Workflows
1. **Cache Dependencies**: Already configured
2. **Parallel Execution**: Matrix strategy enabled
3. **Selective Testing**: Run only changed tests
4. **Reduce Emulator Boot Time**: Use snapshot

### Cost Optimization
1. Use self-hosted runners for frequent execution
2. Limit artifact retention period
3. Schedule non-critical workflows during off-peak hours
4. Use conditional job execution

---

## 🆘 Support & Resources

### Documentation
- `.github/workflows/README.md` - Detailed workflow documentation
- `CI_CD_QUICKSTART.md` - Quick start guide
- GitHub Actions official docs

### Common Issues
| Issue | Solution |
|-------|----------|
| Workflow not triggering | Check branch name and triggers |
| Tests failing | Review logs and screenshots |
| Emulator timeout | Increase timeout or reduce API level |
| Permission denied | Check repository permissions |

### Contact
- Repository: https://github.com/smanna-log/rooms
- Issues: Create GitHub issue for bugs/features

---

## 🎉 Success Criteria

Your CI/CD is successfully set up when:
- ✅ Workflows run automatically on push/PR
- ✅ All tests execute and pass
- ✅ Reports and artifacts are generated
- ✅ Code quality checks pass
- ✅ Security scans complete
- ✅ Team can view and download reports

---

**Next Step**: Push your code to GitHub and watch the magic happen! 🚀
