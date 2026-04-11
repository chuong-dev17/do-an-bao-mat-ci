// Intentional vulnerable patterns for testing Security CI alerts.
// Remove this file after validating the pipeline.

const express = require('express');
const app = express();

// Hardcoded secret example
const API_TOKEN = 'ghp_1234567890_test_token_do_not_use';

app.get('/demo', (req, res) => {
  const userInput = req.query.q;

  // XSS pattern: directly write untrusted input to HTML response
  res.send('<h1>' + userInput + '</h1>');

  // Dangerous eval pattern
  eval(userInput);
});

app.listen(3000);
