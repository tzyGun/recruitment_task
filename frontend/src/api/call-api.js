import axios from "axios";

const getCart = async () => {
    const response = await axios.get('/api/cart')
    const { data } = response
    return data
}

const checkProduct = async (requestData) => axios.post('/api/product/check', requestData)


const handlerApiError = (error) => {
        if(error) {
            console.log(error)
        }
} 

export {
    getCart,
    checkProduct,
    handlerApiError
}


