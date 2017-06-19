import * as types from './mutation-types'

export const setText = ({ commit }, text) => {
  if (product.inventory > 0) {
    commit(types.ADD_TO_CART, {
      id: product.id
    })
  }
}
