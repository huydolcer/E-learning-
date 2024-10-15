const urlParams = new URLSearchParams(window.location.search);
const id = urlParams.get('id');

axios.get(`/learning_edu/Blogs/get_blog/${id}`).then((response) => {
    const data = response.data;
    const dateCreate = data.datecreate;
    const dateObj = new Date(dateCreate);
    let day = dateObj.getDate();
    let month = dateObj.getMonth() + 1;
    let year = dateObj.getFullYear();

    const blogDetails = document.getElementById("blog_details");
    if (blogDetails) {
        const innerHtml = `
            <h1 class="blog-post-title">${data.title}</h1>
            <p class="blog-post-meta">Ngày ${day} tháng ${month}, ${year} bởi <a href="#">${data.user.lastname}</a></p>
            <img src="${data.image}" alt="Elearning" class="img-fluid wow animate__fadeInUp">
            <p class="mt-4 wow animate__fadeInUp" data-wow-delay="0.2s">
                ${data.content}
            </p>
            <h2 class="wow animate__fadeInUp" data-wow-delay="0.4s">Chi tiết hơn</h2>
            <p class="wow animate__fadeInUp" data-wow-delay="0.6s">
                ${data.description}
            </p>`;
        blogDetails.innerHTML = innerHtml;
    } else {
        console.error("Không tìm thấy phần tử blog_details.");
    }
}).catch(error => {
    console.error("Có lỗi xảy ra khi gọi API:", error);
});
