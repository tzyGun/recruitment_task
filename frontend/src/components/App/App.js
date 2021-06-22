import React from 'react';
import './App.css';
import { getCart } from '../../api/call-api';
import { useEffect, useState } from 'react';
import { ProductItem } from '../product/product-item';
import { Loader } from '../loader/loader';
import { Summary } from '../summary/summary';

const App = () => {
  const [appState, setAppState] = useState({ isDataLoaded: false })
  const [products, setProducts] = useState([])
  const [cart, setCart] = useState([])
  const [summary, setSummary] = useState(0.0)

  useEffect(async () => {
    const data = await getCart()
    setProducts((product) => [...product, ...data])
    setAppState({ isDataLoaded: true })
    return () => {
      setProducts([])
    }
  }, [])

  useEffect(() => {
    setSummary(calculateSummary(cart))
    return () => {
    }
  }, [cart])

  const calculateSummary = (array) => {
    const summary = array.reduce(function (previousValue, currentValue) {
      return previousValue + (currentValue.price * currentValue.quantity)
    }, 0);
    return summary
  }

  const resetItem = (array, item) => {
    const index = array.findIndex((el) => el.pid === item.pid)
    array[index] = item
    return [...array]
  }

  const fillCart = (array, item) => {
    if (array.find((el) => el.pid === item.pid)) {
      const index = array.findIndex((el) => el.pid === item.pid)
      array[index] = item
      return [...array]
    } else {
      return [...array, item]
    }
  }
  const viewContent = () => appState.isDataLoaded ?
    products.map((item, index) => <ProductItem key={index} item={item} onErrorCallback={(item) => setCart((prev) => resetItem(prev, item))}
      onCounterChange={(item) => setCart((prev) => fillCart(prev, item))} />) : <Loader />

  return (
    <div className="container">
      <h3>Lista produktów</h3>
      <ul>
        {viewContent()}
      </ul>
      <Summary summary={summary} />
    </div>
  );
};

export {
  App
};
