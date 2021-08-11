import telebot
import config
import random


from telebot import types


bot = telebot.TeleBot(config.TOKEN)


@bot.message_handler(commands=['start'])
def welcome(message):
    sti = open('stikers/sticker.webp', 'rb')
    bot.send_sticker(message.chat.id, sti)
    
    
    #keyboard
    markup = types.ReplyKeyboardMarkup(resize_keyboard=True)
    item1 = types.KeyboardButton("☻ What`s up")
    item2 = types.KeyboardButton("♠ Random number")
    
    markup.add(item1, item2)
    
    bot.send_message(message.chat.id, "Welcome, my bich, {0.first_name}!\nЯ - <b>{1.first_name}</b>, bot was created for test.".format(message.from_user, bot.get_me()),
    parse_mode='html', reply_markup=markup)

@bot.message_handler(content_types=['text'])
def lalala(message):
    if message.chat.type == 'private':
        if message.text == "☻ What`s up":
        
            markup = types.InlineKeyboardMarkup(row_width=2)
            item1 = types.InlineKeyboardButton("Good", callback_data='good')
            item2 = types.InlineKeyboardButton("Not good", callback_data='bad')
        
            markup.add(item1, item2)
        
            bot.send_message(message.chat.id, 'I am good, what about you?', reply_markup=markup)
        elif message.text == "♠ Random number":
            bot.send_message(message.chat.id, str(random.randint(0,100)))
        else:
            bot.send_message(message.chat.id,'I dont know what do you want')
            
@bot.callback_query_handler(func=lambda call: True)
def callback_inline(call):
    try:
        if call.message:
            if call.data == 'good':
                bot.send_message(call.message.chat.id, 'That is good 😊')
            elif call.data == 'bad':
                bot.send_message(call.message.chat.id, 'Shit is happen 😢')
 
            # remove inline buttons
            bot.edit_message_text(chat_id=call.message.chat.id, message_id=call.message.message_id, text="😊 What`s up?",
                reply_markup=None)
 
            # show alert
            bot.answer_callback_query(callback_query_id=call.id, show_alert=False,
                text="ЭТО ТЕСТОВОЕ УВЕДОМЛЕНИЕ!!11")
 
    except Exception as e:
        print(repr(e))

#RUN
bot.polling(none_stop=True)