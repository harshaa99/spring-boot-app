const number = document.getElementById('number')
const form = document.getElementById('form')
const errorElement = document.getElementById('error')

form.addEventListener('submit', (e) => {
  let messages = []
  if (number.value.length <= 12) {
    messages.push('Card number should be longer than 16 digits')
  }

  if (number.value.length >= 19) {
    messages.push('Card number should not be longer than 19 digits')
  }
  if (messages.length > 0) {
	    e.preventDefault()
	    errorElement.innerText = messages.join(', ')
  }
})