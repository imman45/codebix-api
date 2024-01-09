async function catdata(cat) {
  alert("working");
  var caattt = cat.id;
  const url = "http://localhost:8090/student/category/" + caattt;
  var qut;

  try {
    await fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("wrong");
        }
        return response.json();
      })
      .then((data) => {
        console.log(data);
        localStorage.setItem("datas", JSON.stringify(data));
        window.location.href = "practicequiz.html";
      })
      .catch((error) => {
        console.log("pronblrm: " + error);
      });
  } catch (error) {
    console.log(error);
  }
}
