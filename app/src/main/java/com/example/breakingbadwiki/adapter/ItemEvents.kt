package com.example.breakingbadwiki.adapter

import com.example.breakingbadwiki.data.ItemPost

interface ItemEvents {

    fun onItemClicked(itemPost: ItemPost)

    fun onItemLongClicked(itemPost: ItemPost)

}