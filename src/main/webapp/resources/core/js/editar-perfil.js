const inputFile = document.getElementById('fotoPerfil');
const avatarPreview = document.getElementById('avatarPreview');
if (inputFile) {
    inputFile.addEventListener('change', function (e) {
        const file = e.target.files && e.target.files[0];
        if (file) {
            const url = URL.createObjectURL(file);
            avatarPreview.src = url;
        }
    });
}