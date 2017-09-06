package com.github.charbgr.cliffhanger.features.home

import com.github.charbgr.cliffhanger.R

enum class NavigationItem(val id: Int) {
  TOP_RATED(R.id.bottom_navigation_category_top_rated), NOW_PLAYING(
      R.id.bottom_navigation_category_now_playing),
  WATCHLIST(R.id.bottom_navigation_watchlist), POPULAR(
      R.id.bottom_navigation_category_popular),
  UPCOMING(R.id.bottom_navigation_category_upcoming);

  companion object {

    fun valueOf(itemId: Int): NavigationItem {
      when (itemId) {
        R.id.bottom_navigation_category_top_rated -> return TOP_RATED
        R.id.bottom_navigation_category_now_playing -> return NOW_PLAYING
        R.id.bottom_navigation_watchlist -> return WATCHLIST
        R.id.bottom_navigation_category_popular -> return POPULAR
        R.id.bottom_navigation_category_upcoming -> return UPCOMING
      }

      return TOP_RATED
    }
  }
}