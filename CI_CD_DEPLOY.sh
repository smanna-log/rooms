# 🚀 CI/CD Quick Deploy Script

This script will help you push the CI/CD configuration to your GitHub repository.

## 📋 Pre-Deployment Checklist

Run this script to verify everything is ready:

```bash
#!/bin/bash

echo "=================================="
echo "CI/CD Pre-Deployment Checklist"
echo "=================================="
echo ""

# Check if git is initialized
if [ -d ".git" ]; then
    echo "✓ Git repository initialized"
else
    echo "✗ Git not initialized. Run: git init"
    exit 1
fi

# Check GitHub workflows
if [ -d ".github/workflows" ]; then
    WORKFLOW_COUNT=$(ls -1 .github/workflows/*.yml 2>/dev/null | wc -l)
    echo "✓ GitHub workflows found: $WORKFLOW_COUNT"
else
    echo "✗ No workflows found"
    exit 1
fi

# Check documentation
DOCS_COUNT=$(ls -1 *.md 2>/dev/null | wc -l)
echo "✓ Documentation files: $DOCS_COUNT"

# Check remote
if git remote -v | grep -q "github.com"; then
    echo "✓ GitHub remote configured"
    git remote -v
else
    echo "⚠ GitHub remote not configured"
    echo ""
    echo "To add remote, run:"
    echo "  git remote add origin git@github.com:smanna-log/rooms.git"
    echo "  OR"
    echo "  git remote add origin https://github.com/smanna-log/rooms.git"
fi

echo ""
echo "=================================="
echo "Ready to deploy!"
echo "=================================="

```

## 🎯 Deploy in 3 Steps

### Step 1: Initialize Git (if needed)
```bash
cd "/home/wadmin/Downloads/Mobile_automation1 (1)/Mobile_automation"
git init
```

### Step 2: Add Remote Repository
**Option A: Using SSH (Recommended)**
```bash
git remote add origin git@github.com:smanna-log/rooms.git
```

**Option B: Using HTTPS**
```bash
git remote add origin https://github.com/smanna-log/rooms.git
```

### Step 3: Push to GitHub
```bash
# Add all files
git add .

# Commit
git commit -m "feat: Add complete CI/CD pipeline with automated testing

- GitHub Actions workflows for CI/CD
- Automated test execution on Android emulator
- Code quality and security scanning
- Release automation
- Comprehensive documentation"

# Push to main branch
git branch -M main
git push -u origin main
```

## ✅ Verify Deployment

After pushing, verify:

1. **Check Repository**: https://github.com/smanna-log/rooms
2. **View Actions**: https://github.com/smanna-log/rooms/actions
3. **Workflows Should Start Automatically**

## 🔍 What to Expect

### Immediately After Push:
```
✓ CI/CD Pipeline - Mobile Automation (Running)
  ├─ Build & Validate
  └─ Run Tests (if emulator available)

✓ Code Quality & Security Scan (Running)
  ├─ Code Quality Check
  ├─ Security Scan
  └─ Code Coverage
```

### After Completion:
- 📊 Test reports available in Artifacts
- 📸 Screenshots for failed tests
- 🔍 Code quality reports
- 🛡️ Security scan results

## 📊 Files Being Pushed

### Workflows (`.github/workflows/`)
- ✅ ci-cd-pipeline.yml (Main CI/CD)
- ✅ code-quality.yml (Quality & Security)
- ✅ release.yml (Release Automation)
- ✅ README.md (Documentation)
- ✅ ci-cd-config.env (Configuration)
- ✅ setup-ci-cd.sh (Setup Script)
- ✅ .gitignore (Workflow Ignores)

### Documentation (Root)
- ✅ CI_CD_QUICKSTART.md
- ✅ DEPLOYMENT_GUIDE.md
- ✅ CICD_IMPLEMENTATION_SUMMARY.md
- ✅ CI_CD_DEPLOY.sh (This file)

## 🎉 Success Indicators

You'll know it worked when:
1. ✅ No push errors
2. ✅ Workflows appear in Actions tab
3. ✅ Workflows start running automatically
4. ✅ Test reports generated
5. ✅ No workflow errors

## 🆘 Troubleshooting

### Permission Denied (SSH)
```bash
# Test SSH connection
ssh -T git@github.com

# If failed, add SSH key to GitHub
cat ~/.ssh/id_ed25519.pub
# Copy output to GitHub > Settings > SSH Keys
```

### Authentication Failed (HTTPS)
```bash
# Use personal access token instead of password
# Generate token: GitHub > Settings > Developer Settings > Personal Access Tokens
```

### Workflows Not Running
1. Check `.github/workflows/` directory exists
2. Verify YAML syntax
3. Check branch name matches workflow triggers
4. Enable Actions in repository settings

### Tests Failing
1. Download artifacts to see reports
2. Check emulator configuration
3. Review test logs
4. Verify test data

## 📚 Next Steps After Deployment

1. **Monitor First Run**: Watch workflows execute
2. **Download Reports**: Check artifacts section
3. **Customize**: Adjust workflows as needed
4. **Team Setup**: Share access and documentation
5. **Branch Protection**: Set up protection rules

## 🔗 Quick Links

- **Repository**: https://github.com/smanna-log/rooms
- **Actions**: https://github.com/smanna-log/rooms/actions
- **Settings**: https://github.com/smanna-log/rooms/settings

---

**Need Help?** Check the other documentation files:
- `CI_CD_QUICKSTART.md` - Quick start guide
- `DEPLOYMENT_GUIDE.md` - Detailed deployment guide
- `CICD_IMPLEMENTATION_SUMMARY.md` - Complete summary
- `.github/workflows/README.md` - Workflow documentation
