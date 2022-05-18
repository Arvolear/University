def processHeaders(data):
    return data[0].split(',')[1:]

def processData(data):
    music = []

    for row in data[1:]:
        song = row.split(',')
        music.append(dict(
            title=str(song[0]),
            album=str(song[1]),
            band=str(song[2]),
            year=str(song[3]),
            genre=str(song[4]),
            duration=str(song[5]),
            explicitness=str(song[6]),
            producer=str(song[7])
        ))
    return music

def read(filename):
    with open(filename, 'r') as file:
        lines = file.readlines()
        lines = [line.strip() for line in lines]
        return lines

def main():
    data = read('db.csv')
    headers = processHeaders(data)
    music = processData(data)

    match = dict()
    search = dict()
    attempt = 0

    while len(match) != 1 and attempt < 3:
        for header in headers:
            if search.get(header) is None:
                parameter = input(f'Enter value for "{header}" or skip it: ')
            else:
                parameter = input(f'Value for "{header}" is "{search[header]}". Enter new value or skip it: ')

            if parameter:
                search[header] = parameter

        match = dict()

        for song in music:
            if len(search) > 0 and all(song[k] == v for k, v in search.items()):
                match[song["title"]] = song

        if len(match) != 1:
            print(f'{len(match)} songs match parameters {search}.\nPlease be more specific\n')
            attempt += 1

    if len(match) == 0:
        print("Was unable to match any songs")
    else:
        print('Found songs:')
        for song in match.values():
            print(f'"{song["title"]}" by {song["band"]}')

if __name__ == '__main__':
    main()