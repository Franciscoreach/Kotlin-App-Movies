package io.devexpert.kmpmovies.data

class MoviesRepository(private val moviesService: MoviesService) {

    suspend fun fetchPopularMovies(): List<Movie> {
        return moviesService.fetchPopularMovies().results.map { it.toDomainMovie() }
    }

    suspend fun fetchMovieById(id: Int): Movie {
        return moviesService.fetchMovieById(id).toDomainMovie()
    }
}

private fun RemoteMovie.toDomainMovie() = Movie(
    id,
    title,
    overview,
    releaseDate ?: "Fecha no disponible", // Proporciona un valor por defecto si `releaseDate` es nulo
    "https://image.tmdb.org/t/p/w185/${posterPath ?: "defaultPoster.jpg"}", // Usa un valor por defecto si `posterPath` es nulo
    backdropPath?.let { "https://image.tmdb.org/t/p/w780/$it" } ?: "https://image.tmdb.org/t/p/w780/defaultBackdrop.jpg", // Usa un valor por defecto si `backdropPath` es nulo
    originalLanguage ?: "Idioma no disponible", // Proporciona un valor por defecto si `originalLanguage` es nulo
    originalTitle ?: "Título original no disponible", // Proporciona un valor por defecto si `originalTitle` es nulo
    popularity ?: 0.0, // Usa 0.0 si `popularity` es nulo
    voteAverage ?: 0.0 // Usa 0.0 si `voteAverage` es nulo
)

