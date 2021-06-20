import React from 'react'
import { useReducer } from 'react';
import { checkProduct } from '../../api/call-api';

export const Counter = ({ item }) => {
    const initialState = {
        count: 0,
        isButtonDisabled: false,
        isRemoveButtonDisabled: false,
        isAddButtonDisabled: false
    };

    const isProductblocked = () => item?.isBlocked

    const reducer = (state, action) => {
        switch (action.type) {
            case 'increment':
                if (state.count === item.max) {
                    return {
                        count: state.count,
                        isAddButtonDisabled: true
                    }
                }
                return { count: state.count + 1 };
            case 'decrement':
                if (state.count === 0) {
                    return {
                        count: state.count,
                        isRemoveButtonDisabled: true
                    }
                }
                return {
                    count: state.count - 1
                };
            default:
                throw new Error();
        }
    }

    const [state, dispatch] = useReducer(reducer, initialState);
    const isRemoveButtonDisabled = () => state.isRemoveButtonDisabled
    const isAddButtonDisabled = () => state.isAddButtonDisabled

    return (
        <div>
            <button disabled={isProductblocked() || isRemoveButtonDisabled()} onClick={() => dispatch({ type: 'decrement' })}>-</button>
            <button disabled={isProductblocked() || isAddButtonDisabled()} onClick={() => dispatch({ type: 'increment' })}>+</button>
            <span>Obecnie masz {state.count} sztuk produktu: </span>
        </div >
    );
}



