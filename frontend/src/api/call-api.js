import axios from "axios";

const getCart = async () => {
    const response = await axios.get('/api/cart')
    const { data } = response
    return data
}

const checkProduct = async (requestData) => {
    const response = await axios.post('/api/product/check', requestData)
    const { data } = response
    return data
}

export {
    getCart,
    checkProduct
}


