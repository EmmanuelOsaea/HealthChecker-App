function runCheck() {
  const healthScore = Math.floor(Math.random() * 100) + 1;
  let message = '';

  if (healthScore > 80) {
    message = '✅ Excellent! You’re in great shape!';
  } else if (healthScore > 50) {
    message = '⚠️ Fair. Try exercising and eating healthier.';
  } else {
    message = '❌ Poor. You need immediate lifestyle changes!';
  }

  document.getElementById('result').textContent = `Your Health Score: ${healthScore} — ${message}`;
}


async function showHealthTip() {
  try {
    const response = await fetch('data/health-tips.json');
    const data = await response.json();
    const tips = data.tips;

    const randomTip = tips[Math.floor(Math.random() * tips.length)];
    document.getElementById('tip').textContent = `💡 Health Tip: ${randomTip}`;
  } catch (error) {
    console.error('Error loading health tips:', error);
  }
}

// Modify your runCheck function to also call showHealthTip
function runCheck() {
  const healthScore = Math.floor(Math.random() * 100) + 1;
  let message = '';

  if (healthScore > 80) {
    message = '✅ Excellent! You’re in great shape!';
  } else if (healthScore > 50) {
    message = '⚠️ Fair. Try exercising and eating healthier.';
  } else {
    message = '❌ Poor. You need immediate lifestyle changes!';
  }

  document.getElementById('result').textContent = `Your Health Score: ${healthScore} — ${message}`;
  showHealthTip(); // 👈🏿 New line added here
}
