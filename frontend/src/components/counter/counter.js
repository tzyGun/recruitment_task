import React from 'react'
import { useEffect, useState, useCallback } from 'react';
import { checkProduct, handlerApiError } from '../../api/call-api';
import { debounce } from '../../utils/debounce';
import { DEBOUNCE_TIME_MS } from '../../enum/application.enum';


export const Counter = ({ item, onCounterChange, onErrorCallback }) => {
    const [counter, setCounter] = useState(0);

    const isProductblocked = () => item?.isBlocked

    useEffect(() => {
        verify(counter)
        return () => {

        }
    }, [counter])

    const verify = useCallback(
        debounce(counter => checkProduct({
            pid: item.pid,
            price: item.price,
            quantity: counter
        })
            .then(response => {
                onCounterChange({
                    pid: item.pid,
                    price: item.price,
                    quantity: counter
                })
            })
            .catch(err => {
                handlerApiError(err)
                onErrorCallback({
                    price: item.price,
                    pid: item.pid,
                    quantity: 0
                })
                setCounter(0)
            }), DEBOUNCE_TIME_MS),
        []
    );

    const isRemoveButtonDisabled = () => counter == item.min

    const incrementCounter = () => setCounter(counter + 1)
    const decrementCounter = () => setCounter(counter == 0 ? 0 : counter - 1)

    return (
        <div>
            <button disabled={isProductblocked() || isRemoveButtonDisabled()} onClick={() => decrementCounter()}>-</button>
            <button disabled={isProductblocked()} onClick={() => incrementCounter()}>+</button>
            <span>Obecnie masz {counter} sztuk produktu: </span>
        </div>
    );
}



