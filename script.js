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
