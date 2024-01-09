let signupBtn = document.getElementById("signupBtn");
let signinBtn = document.getElementById("signinBtn");
let nameField = document.getElementById("nameField");
let subBtn = document.getElementById("submit");

let user = document.getElementById("username");
let roll = document.getElementById("rollno");
let pass = document.getElementById("password");
let title = document.getElementById("title");

signinBtn.onclick = function () {
  nameField.style.maxHeight = "0";
  title.innerHTML = "Log In";
  signupBtn.classList.add("disable");
  signinBtn.classList.remove("disable");
  subBtn.onclick = Login;
  user.value = "";
  roll.value = "";
  pass.value = "";
};

signupBtn.onclick = function () {
  nameField.style.maxHeight = "65px";
  title.innerHTML = "Sign Up";
  signupBtn.classList.remove("disable");
  signinBtn.classList.add("disable");
  subBtn.onclick = Signup;
  user.value = "";
  roll.value = "";
  pass.value = "";
};

// Login submit button function
async function Login() {
  // event.preventDefault();

  const rollno = document.getElementById("rollno").value;
  const password = document.getElementById("password").value;

  // Validate user input
  if (!rollno || !password.trim()) {
    alert("Please fill all no Empty Spaces");
    return;
  }

  const url = "http://localhost:8091/teacher/login";
  const requestData = {
    rollno: rollno,
    password: password,
  };

  try {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(requestData),
    });

    if (response.status === 200) {
      const data = await response.json();
      alert("Authorized");
      window.location.href = "addquestion.html";
    } else if (response.status === 401) {
      alert("Password not matched, Please try again .");
    } else if (response.status === 204) {
      alert("No user found, Please signup.");
    } else {
      throw new Error("Unexpected status code");
    }
  } catch (error) {
    console.error("Error", error);
  }
}

// Signup submit button function

async function Signup() {
  // event.preventDefault();

  const rollno = document.getElementById("rollno").value;
  const name = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  if (!rollno || !password.trim() || !name.trim()) {
    alert("Please fill all sign  no Empty Spaces");
    return;
  }

  const url = "http://localhost:8091/teacher/signup";
  const requestData = {
    rollno: rollno,
    name: name.trim(),
    password: password.trim(),
  };

  try {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(requestData),
    });

    if (response.status === 201) {
      const data = await response.json();
      alert(data + ".\n\nLogin Your Details...");
      signinBtn.click();
    } else if (response.status === 208) {
      alert("user already exist, Login.");
    } else {
      throw new Error("Unexpected status code");
    }
  } catch (error) {
    console.error("Error", error);
  }
}
