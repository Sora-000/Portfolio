import youtube_dl
import discord
import os


intents = discord.Intents.default()
bot = discord.Client(intents=intents)
com = "="



# message ↓

# <Message id=829679953124524044 
# channel=<TextChannel id=628941494908223489 name='봇-실험실' position=4 nsfw=False news=False category_id=628954154450026511> 
# type=<MessageType.default: 0> 
# author=<Member id=378561593879035916 name='Sora' discriminator='3901' bot=False nick=None 
# guild=<Guild id=628940876432801793 name='코딩 천국' 
# shard_id=None chunked=False member_count=13>> flags=<MessageFlags value=0>>



@bot.event
async def on_ready():
    print('login')
    print(bot.user.name)
    # print(bot.user.id)
    print('----------------------')
    await bot.change_presence(status=discord.Status.online, activity=discord.Game(com+"help"))

@bot.event
async def on_message(message):
    if message.content.startswith(com):
        print("----------------")
        print("    "+message.author.name+"#"+message.author.discriminator+" : "+message.content)
        print("----------------")

    if message.content.startswith(com+"입장"):
        # print("----------------")
        # print(message.author.voice.channel) # 유저의 통화방 이름
        # print("----------------")
        await message.author.voice.channel.connect()
        await message.channel.send("보이스채널에 입장합니다.")

    if message.content.startswith(com+"퇴장"):
        for vc in bot.voice_clients:
            if vc.guild == message.guild:
                # print(vc)# <discord.bot.VoiceClient object at 0x00000170A9F52EE0>
                voice = vc

        await voice.disconnect()
        await message.channel.send("보이스채널에서 퇴장합니다.")
    
    if message.content.startswith(com+"재생"):
        for vc in bot.voice_clients:
            if vc.guild == message.guild:
                voice = vc
            else :
                await message.author.voice.channel.connect()
                voice = vc

        if not message.content.split(" ")[1]:# 노래 링크 입력을 안 했을때
            message.channel.send("유튜브 링크를 입력해 주세요")
            return

        url = message.content.split(" ")[1]
        if os.path.isfile("file/" + url.split('=')[1]+".mp3"):  # 노래 파일이 있을때
            with youtube_dl.YoutubeDL() as ydl:
                info = ydl.extract_info(url, download=False)
                title = info["title"]
        else:                                                   # 노래 파일이 없을때
            await message.channel.send("노래 파일을 다운로드 중 입니다! \n잠시만 기다려 주세요")
            option = {
                'outtmpl' : "file/" + url.split('=')[1],
                'audioformat':'mp3',
                'extractaudio':True,
                'noplaylist': True,
                'nocheckcertificate':True,
                'postprocessors': [{
                'key': 'FFmpegExtractAudio',
                'preferredcodec': 'mp3',
                'preferredquality': '192',
            }]
            }
            with youtube_dl.YoutubeDL(option) as ydl:
                ydl.download([url])
                await message.channel.send("노래 파일 다운로드 완료!")
                info = ydl.extract_info(url, download=False)
                title = info["title"]

        voice.play(discord.FFmpegPCMAudio("file/" + url.split('=')[1] + ".mp3"))
        await message.channel.send(title+"을 재생합니다.")

    if message.content.startswith(com+"help") or message.content.startswith(com+"도움"):
        helpEmbed = discord.Embed(title ='명령어', color =0xaaaaff)
        helpEmbed.add_field(name=com+'입장',value='통화방에 입장합니다.',inline=False)
        helpEmbed.add_field(name=com+'퇴장',value='통화방에서 퇴장합니다.',inline=False)
        helpEmbed.add_field(name=com+'재생 [유튜브 링크]',value='음악을 다운로드 후 재생합니다.',inline=False)
        helpEmbed.set_footer(text=bot.user, icon_url=bot.user.avatar_url)
        await message.channel.send(embed=helpEmbed)

bot.run("NjM2MTU4NDU1NTA1MzU0Nzcw.Xa7iuw.Sl5kyXoWSGiusPUFeybyZOr43hc")