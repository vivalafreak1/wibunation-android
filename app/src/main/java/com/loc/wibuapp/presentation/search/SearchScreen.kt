package com.loc.wibuapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.loc.wibuapp.presentation.Dimension.MediumPadding1
import com.loc.wibuapp.presentation.common.AnimeList
import com.loc.wibuapp.presentation.common.SearchBar
import com.loc.wibuapp.presentation.navgraph.Route

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigate: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchAnime) }
        )
        
        Spacer(modifier = Modifier.height(MediumPadding1))
        state.data?.let {
            val data = it.collectAsLazyPagingItems()
            AnimeList(anime = data, onClick = {navigate(Route.DetailScreen.route)})
        }
    }
}