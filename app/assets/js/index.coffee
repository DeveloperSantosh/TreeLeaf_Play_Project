$ ->
    $.get "/user", (user) ->
        $.each user, (name, password)->
            $('#user').append $("<li>").text user.name