package com.plcoding.clashofclans.presentation



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

import coil.compose.rememberAsyncImagePainter
import com.plcoding.clashofclans.domain.DetailedWarrior
import com.plcoding.clashofclans.domain.SimpleWarrior


@Composable
fun WarriorsScreen(
    state: WarriorsViewModel.WarriorsState,
    onSelectWarrior: (id: String) -> Unit,
    onDismissWarriorDialog: () -> Unit,
    viewModel: WarriorsViewModel
) {
    var searchText by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.hsl(54F, 0.78F, 0.78F))

    ) {
        Text(
            text = "Clash Of Clans API",
            fontSize = 40.sp,
            color = Color.hsl(5F, 0.96F, 0.38F),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .padding(top = 12.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily.Cursive, shadow = Shadow(color = Color.hsl(51F, 1F, 0.30F),offset = Offset(5.0f, 10.0f),blurRadius = 6f)
        ))
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                viewModel.onSearchTextChanged(it)
            },
            label = { Text("Buscar guerrers") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(3.dp, Color.Black,shape = RoundedCornerShape(5.dp))
                .background(Color.hsl(28F, 0.87F, 0.62F))
        )

        if (state.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()

            ) {
                CircularProgressIndicator()
            }
        } else {
            val filteredWarriors = state.warriors.filter {
                it.raresa.contains(searchText, ignoreCase = true) || it.nom.contains(searchText, ignoreCase = true)
            }

            LazyColumn(modifier = Modifier.weight(1f)  ) {
                items(filteredWarriors) { warrior ->
                    WarriorItem(
                        warrior = warrior,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelectWarrior(warrior.id) }
                            .padding(16.dp)
                            .background(Color.hsl(28F, 0.87F, 0.62F))
                    )
                }
            }


            if (state.selectedWarrior != null) {
                WarriorDialog(
                    warrior = state.selectedWarrior,
                    onDismiss = onDismissWarriorDialog,
                    modifier = Modifier
                        .background(Color.hsl(28F, 0.97F, 0.70F))
                        .border(5.dp, Color.Black,shape = RoundedCornerShape(5.dp))



                )
            }
        }
    }
}

@Composable
private fun WarriorDialog(
    warrior: DetailedWarrior,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {


    Dialog(onDismissRequest = onDismiss){
        Column(
            modifier = modifier.padding(16.dp)

        ){
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = warrior.nom,
                    fontSize = 30.sp,
                    color = Color.hsl(5F, 0.96F, 0.38F),


                )


            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = createAnnotatedStringWithColor("Abast:" + " ",warrior.abast))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = createAnnotatedStringWithColor("Espai:" + " ",warrior.espai.toString()))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = createAnnotatedStringWithColor("Velocitat:" + " ",warrior.velocitat.toString()))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = createAnnotatedStringWithColor("Tipus d'atac:" + " ",warrior.tipusAtac))





        }

    }
}

@Composable
private fun WarriorItem(
    warrior: SimpleWarrior,
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier.border(5.dp, Color.Black,shape = RoundedCornerShape(5.dp))
        ,
        verticalAlignment = Alignment.CenterVertically
        
    )
    {
        Image(
            painter = rememberAsyncImagePainter(warrior.imatge),
            contentDescription = null,
            modifier = Modifier.size(90.dp).padding(start = 10.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = modifier.weight(1f)


        ) {


            Spacer(modifier = Modifier.width(16.dp))
            Text(text = createAnnotatedStringWithColor("Nom:" + " ",warrior.nom))
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = createAnnotatedStringWithColor("Raresa:" + " ",warrior.raresa))
            Spacer(modifier = Modifier.height(16.dp))



        }
    }

}


fun createAnnotatedStringWithColor(paraulaTipus: String, textPersonatge: String): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.hsl(5F, 0.96F, 0.38F),fontWeight = FontWeight.Bold, fontSize = 20.sp)) {
            append(paraulaTipus)
        }
        withStyle(style = SpanStyle(fontSize = 20.sp)){append(textPersonatge)}
    }

}