const form = document.getElementById('createTaskForm');
const errorMessage = document.getElementById('error-message');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const title = form.title.value;
    const description = form.description.value;
    const status = form.status.value; // Get the selected status from the dropdown


    // Basic client-side validation
    if (title === '') {
        errorMessage.textContent = 'Please enter a task title.';
        return;
    }

    const newTask = {
        title: title,
        description: description,
        status: status // Include status in the newTask object
    };
    fetch('/api/v1/tasks', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newTask)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Task creation failed');
        }
        return response.json(); 
    })
    .then(data => {
        // Task created successfully, you can:
        // 1. Display a success message
        alert('Task created successfully!'); 
        // 2. Clear the form
        form.reset();
        // 3. Redirect to the task list page
        window.location.href = '/taskdashboard.html'; // Replace with your actual task list page
    })
    .catch(error => {
        console.error('Error creating task:', error);
        errorMessage.textContent = 'An error occurred while creating the task.';
    });
});
