package com.dunk.eats.interactors.recipe_categories

enum class FoodCategories(
    val id: Int,
    val categoryName: String,
    val imageUrl: String,
    val isPopular: Boolean = false
) {
    ERROR(
        0,
        "error",
        "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyB3aWR0aD0iNzUycHQiIGhlaWdodD0iNzUycHQiIHZlcnNpb249IjEuMSIgdmlld0JveD0iMCAwIDc1MiA3NTIiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CiA8cGF0aCBkPSJtMTc3LjEgMjI0LjQ1Yy01LjE2NDEgMC05LjQ3MjcgNC4zMDQ3LTkuNDcyNyA5LjQ3Mjd2MjU1LjczYzAgNS4xNjQxIDQuMzA0NyA5LjQ3MjcgOS40NzI3IDkuNDcyN2gyNzAuOThjLTUuMTA1NSA5LjU1NDctMTAuMDA0IDE4LjU1OS0xNS4yNDIgMjguNDE0aDE1MS41NWMtMTIuMjctMjIuNzg5LTI0Ljg5MS00Ni40NTctMzcuODg3LTcwLjg5MXYtMjIyLjczYzAtNS4xNjQxLTQuMzA0Ny05LjQ3MjctOS40NzI3LTkuNDcyN3ptMCA5LjQ3MjdoMzU5LjkydjE5Ny4yOGwtOC40MzM2LTguMTQwNmMtNi42NjAyLTEyLjUxNi0xMy4wNjYtMjQuNjQ4LTE5Ljk4LTM3LjU5LTIuMDg1OSAzLjk1MzEtNC40MDIzIDguMTUyMy02LjUxMTcgMTIuMTM3bC04MC4yMTUtNzcuMTA1Yy0yLjU1MDgtMi4xMDU1LTQuOTc2Ni0xLjU2MjUtNi44MDg2IDAuMTQ4NDRsLTg2Ljg3MSA5Ni4wNDctNjkuMjYyLTU4LjYwNWMtMS42NjQxLTEuMzMyLTQuMjUzOS0xLjMzMi01LjkxOCAwbC03NS45MjIgNjAuMjM0em0xMzcuMzQgMzcuODg3Yy0xOC4yNTQgMC0zMy4xNTIgMTQuODk4LTMzLjE1MiAzMy4xNTJzMTQuODk4IDMzLjE1MiAzMy4xNTIgMzMuMTUyIDMzLjE1Mi0xNC44OTggMzMuMTUyLTMzLjE1Mi0xNC44OTgtMzMuMTUyLTMzLjE1Mi0zMy4xNTJ6bTAgOS40NzI3YzEzLjEzMyAwIDIzLjY4IDEwLjU0NyAyMy42OCAyMy42OCAwIDEzLjEzMy0xMC41NDcgMjMuNjgtMjMuNjggMjMuNjhzLTIzLjY4LTEwLjU0Ny0yMy42OC0yMy42OGMwLTEzLjEzMyAxMC41NDctMjMuNjggMjMuNjgtMjMuNjh6bTEwNC40OCA0OS40MyA3OC41ODYgNzUuNjI1Yy0xNC4xOTkgMjYuNzQyLTI5LjAyIDU0LjU2Ni00NC4zOTggODMuMzJoLTI3Ni4wMXYtNTkuMzQ0bDc4LjczNC02Mi40NTMgNjkuNzA3IDU5LjA1MWMxLjg2MzMgMS42MTcyIDUuMDI3MyAxLjQxMDIgNi42NjAyLTAuNDQ1MzFsODYuNzE1LTk1Ljc1NHptODkuNjg0IDc0Ljg4NyA1OS43ODkgMTEyLjMzaC0xMTkuNTh6bTAgMTIuODc1Yy0yLjYxNzIgMC00LjczNDQgMi4xMjExLTQuNzM0NCA0LjczNDR2NjEuNTY2YzAgMi42MTMzIDIuMTIxMSA0LjczNDQgNC43MzQ0IDQuNzM0NCAyLjYxNzIgMCA0LjczNDQtMi4xMjExIDQuNzM0NC00LjczNDR2LTYxLjU2NmMwLTIuNjE3Mi0yLjEyMTEtNC43MzQ0LTQuNzM0NC00LjczNDR6bTAgNzUuNzczYy01LjIzMDUgMC05LjQ3MjcgNC4yMzgzLTkuNDcyNyA5LjQ3MjcgMCA1LjIzMDUgNC4yNDIyIDkuNDcyNyA5LjQ3MjcgOS40NzI3czkuNDcyNy00LjI0MjIgOS40NzI3LTkuNDcyN2MwLTUuMjMwNS00LjI0MjItOS40NzI3LTkuNDcyNy05LjQ3Mjd6Ii8+Cjwvc3ZnPgo=",
        true
    ),
    CHICKEN(
        1,
        "Chicken",
        "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=640&q=80",
        true
    ),
    BEEF(
        2,
        "Beef",
        "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=640&q=80",
        true
    ),
    SOUP(
        3,
        "Soup",
        "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=640&q=80"
    ),
    DESSERT(
        4,
        "Dessert",
        "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=640&q=80"
    ),
    VEGETARIAN(
        5,
        "Vegetarian",
        "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=640&q=80"
    ),
    MILK(
        6,
        "Milk",
        "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=640&q=80"
    ),
    VEGAN(
        7,
        "Vegan",
        "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=640&q=80"
    ),
    PIZZA(
        8,
        "Pizza",
        "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=640&q=80",
        true
    ),
    DONUT(
        8,
        "Donut",
        "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=640&q=80"
    )
}