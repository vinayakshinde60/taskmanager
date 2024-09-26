const form = document.getElementById('loginForm');
const errorMessage = document.getElementById('error-message');

form.addEventListener('submit', (event) => {
    event.preventDefault(); // Prevent default form submission

    const username = form.username.value;
    const password = form.password.value;

    // Basic client-side validation (you can add more)
    if (username === '' || password === '') {
        errorMessage.textContent = 'Please enter both username and password.';
        return;
    }

    // Create login request object
    const loginRequest = {
        username: username,
        password: password
    };

    // Send login request to the backend
    fetch('/api/v1/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginRequest)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Login failed');
        }
        return response.json();
    })
    .then(data => {
        // Successful login
        if(data.result === 'success'){
            // Redirect to the desired page after successful login
            window.location.href = '/taskdashboard.html'; // Assuming index.html is your home page
        } else {
            errorMessage.textContent = 'Invalid username or password.';
        }
    })
    .catch(error => {
        console.error('Error during login:', error);
        errorMessage.textContent = 'An error occurred during login. Please try again later.';
    });
});
