package com.packageai.packageai.controllers

import org.springframework.data.domain.Sort.by

import com.google.gson.Gson
import com.packageai.packageai.models.DataHandler


import org.springframework.boot.jackson.JsonComponent
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.xml.crypto.Data


@RestController
@RequestMapping("/")
class ProductController() {

    private val dh = DataHandler(ArrayList<List<String>>())

    init {
       dh.loadData()
    }

   @GetMapping("/closestAddresses")
   fun getAddresses(
         @RequestParam("numClosest", defaultValue = "3") num: Int,
         @RequestParam("address", defaultValue = "toronto") address: String,
  ): ResponseEntity<String> {
        println(num)
        println(address)
       return ResponseEntity.ok(dh.getNClosest(address , num))
   }
   }
