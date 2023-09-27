// Sample data representing requests (you can replace this with real data)
const requestsData = [
    {
        id: 1,
        subject: "Request 1",
        deadlineDate: "2023-09-30",
        deadlineHour: "14:00",
        description: "This is the description for request 1.",
        skills: ["Skill A", "Skill B"],
    },
    {
        id: 2,
        subject: "Request 2",
        deadlineDate: "2023-10-05",
        deadlineHour: "10:30",
        description: "This is the description for request 2.",
        skills: ["Skill C", "Skill D"],
    },
    // Add more request objects here
];

// Function to display requests
function displayRequests() {
    const requestList = document.getElementById("request-list");
    
    // Clear existing content
    requestList.innerHTML = "";
    
    // Iterate through the requestsData and create a card for each request
    requestsData.forEach(request => {
        const requestCard = document.createElement("div");
        requestCard.classList.add("request");
        
        requestCard.innerHTML = `
            <h2>${request.subject}</h2>
            <p><strong>Deadline Date:</strong> ${request.deadlineDate}</p>
            <p><strong>Deadline Hour:</strong> ${request.deadlineHour}</p>
            <p><strong>Description:</strong> ${request.description}</p>
            <p><strong>Skills:</strong> ${request.skills.join(', ')}</p>
            <button onclick="updateRequest(${request.id})">Update</button>
            <button onclick="deleteRequest(${request.id})">Delete</button>
        `;
        
        requestList.appendChild(requestCard);
    });
}

// Function to handle the update button click
function updateRequest(requestId) {
    // Implement your update logic here
    alert(`Update request with ID ${requestId}`);
}

// Function to handle the delete button click
function deleteRequest(requestId) {
    // Implement your delete logic here
    alert(`Delete request with ID ${requestId}`);
}

// Call the displayRequests function to initially load the requests
displayRequests();