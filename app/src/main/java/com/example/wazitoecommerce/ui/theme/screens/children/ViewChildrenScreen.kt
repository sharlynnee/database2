package com.example.wazitoecommerce.ui.theme.screens.children

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.wazitoecommerce.data.ChildViewModel
import com.example.wazitoecommerce.models.Child
import com.example.wazitoecommerce.ui.theme.WazitoECommerceTheme

@Composable
fun ViewChildrenScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var childRepository = ChildViewModel(navController, context)


        val emptyChildState = remember { mutableStateOf(Child("","","","","")) }
        var emptyChildrenListState = remember { mutableStateListOf<Child>() }

        var children = childRepository.allChildren(emptyChildState, emptyChildrenListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All children",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(children){
                    ChildItem(
                        name = it.name,
                        age = it.age,
                        description = it.description,
                        id = it.id,
                        navController = navController,
                        childRepository = childRepository,
                        childImage = it.imageUrl
                    )
                }
            }
        }
    }
}


@Composable
fun ChildItem(name:String, age:String, description:String, id:String,
                navController:NavHostController,
                childRepository:ChildViewModel, childImage:String) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = name)
        Text(text = age)
        Text(text = description)
        Image(
            painter = rememberAsyncImagePainter(childImage),
            contentDescription = null,
            modifier = Modifier.size(250.dp)
        )
        Button(onClick = {
            childRepository.deleteChild(id)
        }) {
            Text(text = "Delete")
        }
        Button(onClick = {
            //navController.navigate(ROUTE_UPDATE_PRODUCTS+"/$id")
        }) {
            Text(text = "Update")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ViewChildrenScreenPreview(){
    WazitoECommerceTheme {
        ViewChildrenScreen(navController = rememberNavController())
    }
}