# Azure Cognitive Search: Utilizando AI Search para indexação e consulta de Dados

## Nesse projeto da Azure Cognitive Search, disponível no Bootcamp da DIO em parceria com a Avanade temos a proposta de criar uma solução de pesquisa cognitiva do Azure:

`Confira o passo a passo:`

![Passo 1](https://github.com/user-attachments/assets/589b032c-2b26-4b4a-be9a-ce028995e102)
![Passo 2](https://github.com/user-attachments/assets/ec88e86c-cbb6-48f3-bc36-e4d6eb4fb8a2)
![Passo 3](https://github.com/user-attachments/assets/1e287f52-8af1-410a-9442-fa1d832b2aad)
![Passo 4](https://github.com/user-attachments/assets/bdf6c96a-95eb-4643-acd8-45f21959c10b)
![Passo 5](https://github.com/user-attachments/assets/5cfdb497-f9cd-4dc9-a88c-fc0baf936e47)
![Passo 6](https://github.com/user-attachments/assets/b631f215-f7cf-45c5-bb0e-d562aa76a2ad)
![Passo 7](https://github.com/user-attachments/assets/f878efd2-ecc1-435e-8ca9-7a2e1a408771)
![Passo 8](https://github.com/user-attachments/assets/e6da94ff-5c90-4513-8cdc-5881d8685c1c)
![Passo 9](https://github.com/user-attachments/assets/fecd6486-44c6-4681-9d24-79dc1be1507f)
![Passo 10](https://github.com/user-attachments/assets/68e8792e-a912-47d8-9b1e-57598d9df6fb)
![Passo 11](https://github.com/user-attachments/assets/521319ce-160f-4375-9200-b89c7d900505)
![Passo 12](https://github.com/user-attachments/assets/4fe91b4a-e63c-48d3-9737-b2894d8d7006)
![Passo 13](https://github.com/user-attachments/assets/3cb46a2a-b363-4a28-9b3f-c3699e5740f3)
![Passo 14](https://github.com/user-attachments/assets/52d6ef17-1862-40f1-a41a-b0e2b419f359)
![Passo 15](https://github.com/user-attachments/assets/6d98dd0c-8f26-4d19-827f-2bb35e984084)
![Passo 16](https://github.com/user-attachments/assets/826051e1-3e2f-4f43-b638-7927e473ab7b)
![Passo 17](https://github.com/user-attachments/assets/f72082ba-c9f5-452c-927a-b7367262b5e8)
![Passo 18](https://github.com/user-attachments/assets/0ae058f2-d4fa-4a78-b62f-2cb2509e5ccb)
![Passo 19](https://github.com/user-attachments/assets/cfcb1725-65ef-472a-b6f8-dc5d3dcc802e)
![Passo 20](https://github.com/user-attachments/assets/876841cf-65f5-40cd-beb1-2cebb878d0b9)
![Passo 21](https://github.com/user-attachments/assets/427792e8-fa32-4339-9cb9-5d768e628891)
![Passo 22](https://github.com/user-attachments/assets/5c650e3a-82c3-4e4b-94c4-fa16d1d1e90c)
![Passo 23](https://github.com/user-attachments/assets/281c8fb0-64c3-43a8-a0b7-81ec64194c5a)
![Passo 24](https://github.com/user-attachments/assets/ac6e4c10-e06a-421f-b558-6d058f4cf2d2)
![Passo 25](https://github.com/user-attachments/assets/2144ee3d-c41f-4a2b-8085-07d7a6cf99e2)
![Passo 26](https://github.com/user-attachments/assets/8545bf63-5bad-4dd2-8ef4-35f665fb474a)
![Passo 27](https://github.com/user-attachments/assets/cab5517c-0959-4488-8e5c-e2a7fcade9da)
![Passo 28](https://github.com/user-attachments/assets/f6015133-ee9a-4c9b-abc5-d7e0cf408950)
![Passo 29](https://github.com/user-attachments/assets/4d1ee6c0-076a-4a99-9a2d-0d7af56125ed)
![Passo 30](https://github.com/user-attachments/assets/039150e0-0b03-4a05-af27-7657bee29290)
![Passo 31](https://github.com/user-attachments/assets/8de3c4cc-6b97-40d0-91ea-5760bb7bdf79)
![Passo 32](https://github.com/user-attachments/assets/917216da-bf14-41f3-a18e-c7d25dc20687)
> Imagem mostrada em aula

![Passo 33](https://github.com/user-attachments/assets/786d4b27-5c04-4952-8ee2-3163b0b070a1)
> Imagem mostrada em aula

> ⚠️⚠️ OBS: Na parte de buscar os dados no meu não funcionou e retornou: (Imagem mostrada é o resultado esperado, mostrado em aula. Não busca minha)*

```JSON
{
  "@odata.context": "https://aiservices002.search.windows.net/indexes('azureblob-index')/$metadata#docs(*)",
  "@odata.count": 3,
  "value": [
    {
      "@search.score": 0.6053572,
      "content": "\n\nReview: The coffee tastings every Wednesday afternoon are so fun. Each month there is a new drink theme. You do need to book a spot in advance to attend. It is very worth it! I also love their local music. Fourth Coffee brings in rising artists every weekend. I like to head over there mid-afternoon on weekdays when it’s not too busy and get a slice of pie or their seasonal baked goods.  \nDate: August 13, 2018\nLocation: Chicago, Illinois  \n\nimage1.png\n\nimage2.png\n\n",
      "metadata_storage_path": "aHR0cHM6Ly9zdG9yYWdlYWNjb3VudDAwNC5ibG9iLmNvcmUud2luZG93cy5uZXQvY29mZmVyZXZpZXdzL3Jldmlldy00LmRvY3g1"
    },
    {
      "@search.score": 0.5143011,
      "content": "\nReview: I often make Fourth Coffee my meeting spot for my client meetings weekday mornings. I own a small business and the folks who work at Fourth Coffee are always very friendly. It leaves a good impression on my clients. There are also plenty of drink selections, good wi-fi, and seating. Some of my favorite coffees are the lavender honey latte and, in the winter, the apple-chai latte. There are delicious baked goods offered as well. \nDate: October 21, 2018\nLocation: Chicago, Illinois \n\nimage1.png\n\n",
      "metadata_storage_path": "aHR0cHM6Ly9zdG9yYWdlYWNjb3VudDAwNC5ibG9iLmNvcmUud2luZG93cy5uZXQvY29mZmVyZXZpZXdzL3Jldmlldy01LmRvY3g1"
    },
    {
      "@search.score": 0.23398489,
      "content": "Review: Today I was truly disappointed with how long I had to wait for the pastries I ordered ahead of time. When I got my box, some of the pastries seemed stale. Terrible experience!  \nDate: October 23, 2018\nLocation: Chicago, Illinois \n\n",
      "metadata_storage_path": "aHR0cHM6Ly9zdG9yYWdlYWNjb3VudDAwNC5ibG9iLmNvcmUud2luZG93cy5uZXQvY29mZmVyZXZpZXdzL3Jldmlldy04LmRvY3g1"
    }
  ]
}
```
> ## 📌 Se alguém souber como resolver estarei abrindo uma `issues`

<br>

<div>
	
| ![Jamyle Elen][img] |
|:--------------------:|
| **Jamyle Elen**      |
| **Desenvolvedora Full-Stack**     |

</div>

[img]: https://github.com/user-attachments/assets/4b3637cc-e1a0-45e4-af1b-6b37f3626ecb
